package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Alquiler {

	private Integer idalquiler;
	private Integer idfactura;
	private String matricula;
	private Date fechaInicio;
	private Date fechaFin;
	private Double precio;
	
	
	public Alquiler(Integer idalquiler, Integer idfactura, String matricula, Date fechaInicio, Date fechaFin,
			Double precio) {
		super();
		this.idalquiler = idalquiler;
		this.idfactura = idfactura;
		this.matricula = matricula;
		this.fechaInicio = fechaInicio;
		this.fechaFin= fechaFin;
		this.precio = precio;
	}


	public Integer getIdalquiler() {
		return idalquiler;
	}


	public Integer getIdfactura() {
		return idfactura;
	}


	public String getMatricula() {
		return matricula;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setIdalquiler(Integer idalquiler) {
		this.idalquiler = idalquiler;
	}


	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
	
	 
	
	
	
	
	
}
