package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Camion extends Vehiculo{

	private int numruedas;
	private double mma;
	
	/**
	 * Constructor del camión que llama al de vehículo
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
	 * @param numruedas
	 * @param mma
	 */
	public Camion(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet, int numruedas, double mma) {
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

	public void setNumruedas(int numruedas) {
		this.numruedas = numruedas;
	}

	public void setMma(double mma) {
		this.mma = mma;
	}
	
	
	
	
	
}
