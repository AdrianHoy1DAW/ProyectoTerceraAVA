package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Coche extends Vehiculo{

	
	private int numplazas;
	private int numpuertas;
	
	public Coche(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet, int numplazas, int numpuertas) {
		super(matricula, preciodia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.numplazas = numplazas;
		this.numpuertas = numpuertas;
	}

	public int getNumplazas() {
		return numplazas;
	}

	public int getNumpuertas() {
		return numpuertas;
	}

	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}

	public void setNumpuertas(int numpuertas) {
		this.numpuertas = numpuertas;
	}

	
	
}
