package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Furgoneta extends Vehiculo{

	private double mma;
	/**
	 * Constructor de la furgoneta que hereda de vehículo
	 * @param matricula
	 * @param preciodia
	 * @param marca
	 * @param descripcion
	 * @param color
	 * @param motor
	 * @param cilindrada
	 * @param fechaadq
	 * @param estado
	 * @param carnet
	 * @param mma
	 */
	public Furgoneta(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet, double mma) {
		super(matricula, preciodia, marca, descripcion, color, motor, cilindrada, fechaadq, estado, carnet);
		this.mma = mma;
	}

	public double getMma() {
		return mma;
	}

	public void setMma(double mma) {
		this.mma = mma;
	}
	
	
	
	
}

