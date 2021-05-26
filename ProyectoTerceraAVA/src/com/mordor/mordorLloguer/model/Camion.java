package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Camion extends Vehiculo{

	private int numruedas;
	private double mma;
	
	public Camion(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, char carnet, int numruedas, double mma) {
		super(matricula, preciodia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.numruedas = numruedas;
		this.mma = mma;
	}

	public int getNumruedas() {
		return numruedas;
	}

	public double getMma() {
		return mma;
	}
	
	
	
	
	
}
