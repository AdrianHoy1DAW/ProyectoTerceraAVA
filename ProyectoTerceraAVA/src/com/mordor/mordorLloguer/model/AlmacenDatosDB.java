package com.mordor.mordorLloguer.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AlmacenDatosDB {

	public final int ASCENDENTE = 1;
	public final int DESCENDENTE = 2;
	public final String CAR = "COCHE";
	public final String VAN = "FURGONETA";
	public final String TRUCK = "CAMION";
	public final String MINIBUS = "MICROBUS";
	
	public ArrayList<Empleado> getEmpleados();
	public ArrayList<Empleado> getEmpleadoPorCP(String cp);
	public ArrayList<Empleado> getEmpleadoPorCargo(String cargo);
	public Empleado getEmpleadoPorDNI(String DNI);
	public boolean updateEmpleado(Empleado empleado);
	public boolean deleteEmpleado(String DNI);
	public boolean insertEmpleado(Empleado empleado) throws Exception;
	public boolean authenticate(String login ,String password);
	public ArrayList<Empleado> getEmpleadosOrder(String order, int ad);
	
	public ArrayList<Cliente> getClientes();
	public boolean deleteCustomer(String DNI);
	public boolean grabarCliente(Cliente cliente);
	public boolean updateCustomer(Cliente cliente);
	
	public ArrayList<Vehiculo> getCoche() throws SQLException;
	public ArrayList<Vehiculo> getCamion() throws SQLException;
	public ArrayList<Vehiculo> getFurgoneta() throws SQLException;
	public ArrayList<Vehiculo> getMicroBus() throws SQLException;
	
	
	
	
	
}
