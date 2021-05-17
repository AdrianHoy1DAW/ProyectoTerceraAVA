package com.mordor.mordorLloguer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

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
				
				empleado = new Empleado(rs.getInt("idEmpleado"),
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
	
}
