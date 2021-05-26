package com.mordor.mordorLloguer.model;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

public class MyOracleDataBase implements AlmacenDatosDB {

	private ArrayList<Empleado> getCustomEmpleados(String where, String order) {
		
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "SELECT * FROM EMPLEADO ";
		if(where != null) 
			query += "WHERE " + where;
		if(order != null) {
			query += "ORDER BY " + order;
		}
		
		
		
		
		try(Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);) {
			
			Empleado empleado;
			
			while(rs.next()) {
				
				empleado = new Empleado(
										rs.getString("DNI"),
										rs.getString("nombre"),
										rs.getString("apellidos"),
										rs.getString("CP"),
										rs.getString("email"),
										rs.getDate("fechaNac"),
										rs.getString("cargo"),
										rs.getString("domicilio"),
										rs.getString("password"));	
		
				empleados.add(empleado);
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empleados;
		
		
	}
	
	@Override
	public ArrayList<Empleado> getEmpleados() {
		
		return getCustomEmpleados(null,null);

	}

	@Override
	public ArrayList<Empleado> getEmpleadoPorCP(String cp) {
	
		return getCustomEmpleados("CP=" + cp,null);
	}

	@Override
	public ArrayList<Empleado> getEmpleadoPorCargo(String cargo) {
		
		return getCustomEmpleados("cargo='" +cargo + "'",null);
	}
	
	@Override
	public Empleado getEmpleadoPorDNI(String DNI) {
		
		ArrayList<Empleado> empleado = getCustomEmpleados("dni='"+DNI+"'",null);
		if(empleado.size() != 0)
			return empleado.get(0);
		else
			return null;
		
	}
	
	@Override

	public boolean updateEmpleado(Empleado empleado) {

	boolean actualizado = false;

	DataSource ds = MyDataSource.getOracleDataSource();

	try (Connection con = ds.getConnection();

	Statement stmt = con.createStatement();) {

	String query = "UPDATE EMPLEADO SET nombre='"+empleado.getNombre()+"', "+ 
					"apellidos='"+empleado.getApellidos()+"',"+ 
					((empleado.getDomicilio() != null)?"domicilio='"+empleado.getDomicilio()+"',":"")+ 
					((empleado.getCP() != null)?"CP='"+empleado.getCP()+"',":"")+ 
					"email='"+empleado.getEmail()+"',"+ 
					"fechaNac=TO_DATE('" +empleado.getFechaNac()+"','yyyy-mm-dd'),"+ 
					"cargo='"+empleado.getCargo()+"' "+ 
					"WHERE DNI='" + empleado.getDNI() +"'";

	
	stmt.executeUpdate(query);

	actualizado = (stmt.executeUpdate(query)==1)?true:false;

	} catch (SQLException e) {

	e.printStackTrace();

	}

	return actualizado;

	}

	@Override
	public boolean deleteEmpleado(String DNI) {
		
		boolean actualizado = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "DELETE FROM EMPLEADO WHERE DNI = ?";
		
		try (Connection con = ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);) {
			
			
			
			
		
			stmt.setString(1, DNI);
			
			
			actualizado = (stmt.executeUpdate()==1)?true:false;
			

			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		return actualizado;
	}

	@Override
	public boolean authenticate(String login, String password) {
		
		boolean registrado = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "Select count(*) FROM EMPLEADO WHERE DNI=? and password=ENCRYPT_PASWD.encrypt_val(?)";
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);) {
			
			int pos=0;
			pstmt.setString(++pos, login);
			pstmt.setString(++pos, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1) == 1) {
				registrado = true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return registrado;
	}

	@Override
	public ArrayList<Empleado> getEmpleadosOrder(String order, int ad) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		
		if (ad == AlmacenDatosDB.ASCENDENTE) {
			empleados = getCustomEmpleados(null,order); 
		} else {
			empleados = getCustomEmpleados(null,order + " desc"); 
		}
		
		return empleados;
	}

	@Override
	public boolean insertEmpleado(Empleado empleado) throws Exception {
		
		boolean insertado = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "INSERT INTO EMPLEADO (DNI,NOMBRE,APELLIDOS,DOMICILIO,CP,EMAIL,FECHANAC,CARGO,PASSWORD) VALUES (?,?,?,?,?,?,?,?,ENCRYPT_PASWD.encrypt_val(?))" ;
		
		try (Connection con = ds.getConnection();

		PreparedStatement pstmt = con.prepareStatement(query);) {

		int pos = 0;
		pstmt.setString(++pos, empleado.getDNI());
		pstmt.setString(++pos, empleado.getNombre());
		pstmt.setString(++pos, empleado.getApellidos());
		pstmt.setString(++pos, empleado.getDomicilio());
		pstmt.setString(++pos, empleado.getCP());
		pstmt.setString(++pos, empleado.getEmail());
		pstmt.setDate(++pos, empleado.getFechaNac());
		pstmt.setString(++pos, empleado.getCargo());
		pstmt.setString(++pos, empleado.getPassword());

		

		
		
		
		insertado = (pstmt.executeUpdate()==1)?true:false;
		
		
		}
		
		return insertado;
		
		
		
	}

	@Override
	public ArrayList<Cliente> getClientes() {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		ResultSet rs = null;
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{ call ?:=GESTIONALQUILER.LISTARCLIENTES() }";
		
		
		
		
		try(Connection con = ds.getConnection();
			CallableStatement cstmt = con.prepareCall(query);) {
			
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstmt.execute();
			
			rs = (ResultSet) cstmt.getObject(1);
			
			Cliente empleado;
			
			while(rs.next()) {
				
			
				
				empleado = new Cliente(
										rs.getString("DNI"),
										rs.getString("nombre"),
										rs.getString("apellidos"),
										rs.getString("domicilio"),
										rs.getString("cp"),
										rs.getString("email"),
										rs.getDate("fechanac"),
										rs.getString("carnet").charAt(0),
										rs.getBytes("foto"));	
		
				clientes.add(empleado);
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return clientes;
		
		
		
	}

	@Override
	public boolean deleteCustomer(String DNI) {
		
		boolean borrado = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{ call GESTIONALQUILER.BORRARCLIENTE(?) }";
		
		
		
		
		try(Connection con = ds.getConnection();
			CallableStatement cstmt = con.prepareCall(query);) {
			
			cstmt.setString(1, DNI);
			
			borrado = (cstmt.executeUpdate() == 1)?true:false;
			
		
		
		
		
	} catch (SQLException e) {
		
		
		e.printStackTrace();
	}
		return borrado;
	
	}

	@Override
	public boolean grabarCliente(Cliente cliente) {
		
		boolean grabado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{ call GESTIONALQUILER.GRABARCLIENTE(?,?,?,?,?,?,?,?,?)}";
		
		try(Connection con = ds.getConnection();
				CallableStatement cstmt = con.prepareCall(query);) {
			
			cstmt.setString(1, cliente.getDNI());
			cstmt.setString(2, cliente.getNombre());
			cstmt.setString(3, cliente.getApellidos());
			cstmt.setString(4, cliente.getDomicilio());
			cstmt.setString(5, cliente.getCp());
			cstmt.setString(6, cliente.getEmail());
			cstmt.setDate(7, cliente.getFechaNac());
			cstmt.setString(8, String.valueOf(cliente.getCarnet()));
			cstmt.setBytes(9, cliente.getFoto());
			
			grabado = (cstmt.executeUpdate() == 1)?true:false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return grabado;
	}

	@Override
	public boolean updateCustomer(Cliente cliente) {
		
		return grabarCliente(cliente);
	}

	
	private ArrayList<Vehiculo> getVehiculos(String tipo) throws SQLException {
		
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		ResultSet rs = null;
		
		String query = "{ call GESTIONVEHICULOS.listarvehiculos(?,?)}";
		
		try (Connection con = ds.getConnection();
				CallableStatement cstmt = con.prepareCall(query);) {
			
			cstmt.setString(1, tipo);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
			
			rs = (ResultSet) cstmt.getObject(2);
			
			while(rs.next()) {
				
				String matricula = rs.getString("c1");
				Double preciodia = rs.getDouble("n1");
				String marca = rs.getString("c2");
				String descripcion = rs.getString("c3");
				String color = rs.getString("c4");
				String motor = rs.getString("c5");
				Double cilindrada = rs.getDouble("n2");
				Date fechaadq = rs.getDate("c6");
				String estado = rs.getString("c7");
				char carnet = rs.getString("c8").charAt(0);
				
				if(tipo.equals(CAR)) {
					
					int numplazas = rs.getInt("n3");
					int numpuertas = rs.getInt("n4");
					
					vehiculos.add(new Coche(matricula, preciodia, marca, descripcion, color
							,motor,cilindrada,fechaadq,estado,carnet,numplazas,numpuertas));
					
				} else if(tipo.equals(TRUCK)) {
					
					int numruedas = rs.getInt("n3");
					double mma = rs.getDouble("n4");
					

					vehiculos.add(new Camion(matricula, preciodia, marca, descripcion, color
							,motor,cilindrada,fechaadq,estado,carnet,numruedas,mma));
					
					
				} else if(tipo.equals(VAN)) {
					
					double mma = rs.getDouble("n3");
					

					vehiculos.add(new Furgoneta(matricula, preciodia, marca, descripcion, color
							,motor,cilindrada,fechaadq,estado,carnet,mma));
					
					
				} else if(tipo.equals(MINIBUS)) {
					
					int numplazas = rs.getInt("n3");
					int medida = rs.getInt("n4");
					

					vehiculos.add(new Microbus(matricula, preciodia, marca, descripcion, color
							,motor,cilindrada,fechaadq,estado,carnet,numplazas,medida));
					
					
				}
				
			}
			
		}
		
		if(rs != null) {
			rs.close();
		}
		
		
		
		return vehiculos;
	}

	@Override
	public ArrayList<Vehiculo> getCoche() throws SQLException {
		
		
		
		return getVehiculos(CAR);
	}

	@Override
	public ArrayList<Vehiculo> getCamion() throws SQLException {
		
		return getVehiculos(TRUCK);
	}

	@Override
	public ArrayList<Vehiculo> getFurgoneta() throws SQLException {
		
		return getVehiculos(VAN);
	}

	@Override
	public ArrayList<Vehiculo> getMicroBus() throws SQLException {
		
		return getVehiculos(MINIBUS);
	}
	
	
}	
