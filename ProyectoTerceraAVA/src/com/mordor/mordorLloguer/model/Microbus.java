package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Microbus extends Vehiculo {

	private int numplazas;
	private double medida;
	/**
	 * Constructor del microbus que hereda de vehículo
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
	 * @param numplazas
	 * @param medida
	 */
	public Microbus(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet, int numplazas, double medida) {
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

	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}

	public void setMedida(double medida) {
		this.medida = medida;
	}
	
	
	
	
	
	
	
	
}
