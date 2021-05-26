package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Vehiculo {
	
	private String matricula;
	private double preciodia;
	private String marca;
	private String descripcion;
	private String color;
	private String motor;
	private double cilindrada;
	private Date fechaadq;
	private String estado;
	private char carnet;
	
	public Vehiculo(String matricula, double preciodia, String marca, String descripcion, String color, String motor,
			double cilindrada, Date fechaadq, String estado, char carnet) {
		super();
		this.matricula = matricula;
		this.preciodia = preciodia;
		this.marca = marca;
		this.descripcion = descripcion;
		this.color = color;
		this.motor = motor;
		this.cilindrada = cilindrada;
		this.fechaadq = fechaadq;
		this.estado = estado;
		this.carnet = carnet;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public double getPreciodia() {
		return preciodia;
	}
	public String getMarca() {
		return marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getColor() {
		return color;
	}
	public String getMotor() {
		return motor;
	}
	public double getCilindrada() {
		return cilindrada;
	}
	public Date getFechaadq() {
		return fechaadq;
	}
	public String getEstado() {
		return estado;
	}
	public char getCarnet() {
		return carnet;
	}
	
	
	
}
