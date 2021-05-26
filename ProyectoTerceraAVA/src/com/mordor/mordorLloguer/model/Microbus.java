package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Microbus extends Vehiculo {

	private int numplazas;
	private double medida;
	
	public Microbus(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, char carnet, int numplazas, double medida) {
		super(matricula, preciodia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.numplazas = numplazas;
		this.medida = medida;
	}

	public int getNumplazas() {
		return numplazas;
	}

	public double getMedida() {
		return medida;
	}
	
	
	
	
	
	
	
	
}
