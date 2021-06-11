package com.mordor.mordorLloguer.model;

import java.sql.Date;

public class Factura {
	
	private int idfactura;
	private Date fecha;
	private Double importebase;
	private Double importeiva;
	private int clienteid;
	/**
	 * Constructor de la factura con todos sus campos
	 * @param idfactura
	 * @param fecha
	 * @param importebase
	 * @param importeiva
	 * @param clienteid
	 */
	public Factura(int idfactura, Date fecha, Double importebase, Double importeiva, int clienteid) {
		super();
		this.idfactura = idfactura;
		this.fecha = fecha;
		this.importebase = importebase;
		this.importeiva = importeiva;
		this.clienteid = clienteid;
	}

	public int getIdfactura() {
		return idfactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public Double getImportebase() {
		return importebase;
	}

	public Double getImporteiva() {
		return importeiva;
	}

	public int getClienteid() {
		return clienteid;
	}
	
	
	

}
