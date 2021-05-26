package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Furgoneta extends Vehiculo{

	private double mma;

	public Furgoneta(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, char carnet, double mma) {
		super(matricula, preciodia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.mma = mma;
	}

	public double getMma() {
		return mma;
	}
	
	
	
	
}

