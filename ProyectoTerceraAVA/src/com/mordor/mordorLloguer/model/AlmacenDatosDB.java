package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
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
	
	public ArrayList<Coche> getCoche() throws SQLException, ParseException;
	public ArrayList<Camion> getCamion() throws SQLException, ParseException;
	public ArrayList<Furgoneta> getFurgoneta() throws SQLException, ParseException;
	public ArrayList<Microbus> getMicroBus() throws SQLException, ParseException;
	
	public boolean deleteCar(String matricula) throws SQLException;
	public boolean deleteTruck(String matricula) throws SQLException;
	public boolean deleteVan(String matricula) throws SQLException;
	public boolean deleteMinibus(String matricula) throws SQLException;
	
	public boolean updateCar(Coche c) throws SQLException;
	public boolean updateTruck(Camion c) throws SQLException;
	public boolean updateVan(Furgoneta f) throws SQLException;
	public boolean updateMinibus(Microbus m) throws SQLException;
	
	public boolean addCar(Coche c) throws SQLException;
	public boolean addTruck(Camion c) throws SQLException;
	public boolean addVan(Furgoneta f) throws SQLException;
	public boolean addMinibus(Microbus m) throws SQLException;
	
	public ArrayList<Factura> getFacturas() throws SQLException;
	public ArrayList<Alquiler> getAlquiler() throws SQLException;
	public boolean insertarAlquiler(Integer idfactura, String DNI, String matricula, Date fechainicio, Date fechafinal) throws SQLException;
	public boolean insertarFactura(String DNI, String matricula, Date fechainicio, Date fechafinal) throws SQLException;
	public boolean borrarAlquiler(Integer idfactura) throws SQLException;
	
	
	
}
