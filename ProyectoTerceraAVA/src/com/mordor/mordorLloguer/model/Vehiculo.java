package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Vehiculo {
	
	private String matricula;
	private Double preciodia;
	private String marca;
	private String descripcion;
	private String color;
	private String motor;
	private Double cilindrada;
	private Date fechaadq;
	private String estado;
	private char carnet;
	
	public Vehiculo(String matricula,Double preciodia, String marca, String descripcion, String color, String motor,
			Double cilindrada, Date fechaadq, String estado, char carnet) {
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
	public Double getCilindrada() {
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

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setPreciodia(double preciodia) {
		this.preciodia = preciodia;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}

	public void setFechaadq(Date fechaadq) {
		this.fechaadq = fechaadq;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setCarnet(char carnet) {
		this.carnet = carnet;
	}
	
	
	
	
}
