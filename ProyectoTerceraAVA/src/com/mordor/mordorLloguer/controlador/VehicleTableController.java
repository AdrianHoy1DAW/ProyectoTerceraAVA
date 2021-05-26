package com.mordor.mordorLloguer.controlador;

import java.sql.SQLException;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.view.VehicleView;

public class VehicleTableController {

	private AlmacenDatosDB modelo;
	private VehicleView vista;
	
	public VehicleTableController(AlmacenDatosDB modelo, VehicleView vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
	}
	
	private void inicializar() {
		
		try {
			System.out.println(modelo.getCoche());
			System.out.println(modelo.getCamion());
			System.out.println(modelo.getFurgoneta());
			System.out.println(modelo.getMicroBus());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void go() {
		inicializar();
	}
	
	
}
