package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.Arrays;

public class Cliente {

	private int idcliente;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String cp;
	private String email;
	private Date fechaNac;
	private char carnet;
	private byte[] foto;
	/**
	 * Constructor de cliente al que no se le pasa el id
	 * @param dNI
	 * @param nombre
	 * @param apellidos
	 * @param domicilio
	 * @param cp
	 * @param email
	 * @param fechaNac
	 * @param carnet
	 * @param foto
	 */
	public Cliente(String dNI, String nombre, String apellidos, String domicilio, String cp, String email,
			Date fechaNac, char carnet, byte[] foto) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.cp = cp;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.foto = foto;
	}
	/**
	 *  Constructor de cliente al que  se le pasa el id
	 * @param idcliente
	 * @param dNI
	 * @param nombre
	 * @param apellidos
	 * @param domicilio
	 * @param cp
	 * @param email
	 * @param fechaNac
	 * @param carnet
	 * @param foto
	 */
	public Cliente(int idcliente,String dNI, String nombre, String apellidos, String domicilio, String cp, String email,
			Date fechaNac, char carnet, byte[] foto) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.cp = cp;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.foto = foto;
		this.idcliente = idcliente;
	}



	public String getDNI() {
		return DNI;
	}



	public String getNombre() {
		return nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public String getDomicilio() {
		return domicilio;
	}



	public String getCp() {
		return cp;
	}



	public String getEmail() {
		return email;
	}



	public Date getFechaNac() {
		return fechaNac;
	}



	public char getCarnet() {
		return carnet;
	}



	public byte[] getFoto() {
		return foto;
	}


	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Cliente) {
			return ((Cliente)o).getDNI().compareTo(DNI) == 0;
		} else {
			return false;
		}
		
	}
	
	

	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", domicilio=" + domicilio
				+ ", cp=" + cp + ", email=" + email + ", fechaNac=" + fechaNac + ", carnet=" + carnet + ", foto="
				+ Arrays.toString(foto) + "]";
	}

	public int getIdcliente() {
		return idcliente;
	}
	
	
	
	
	
}
