package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyInvoiceTableModel extends MyTableModel<Alquiler>{

	
	private List<Vehiculo> datas;
	/**
	 * Constructor del modelo de tabla de factura al que se le pasa los alquileres y los coches correspondientes a ese alquiler
	 * @param data
	 * @param datas
	 */
	public MyInvoiceTableModel(List<Alquiler> data,List<Vehiculo> datas) {
		super(data, new ArrayList<String>(Arrays.asList(new String[]{"Modelo","Matricula","Importe","FechaInicio","FechaFin"})));
		
		this.datas = datas;
	}
	/**
	 * Método que rellena la tabla 
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 0:
			return datas.get(row).getMarca();
		case 1:
			return datas.get(row).getMatricula();
		case 2:
			return data.get(row).getPrecio();
		case 3:
			return data.get(row).getFechaInicio();
		case 4:
			return data.get(row).getFechaFin();
		
		}
		return null;
	}
	/**
	 * Método que dice que celdas son editables por el usuario
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		
		switch(column) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
			return false;
			default : return true;
		}
		
		
	}
	/**
	 *  Método que indica el tipo de columna que hay en la tabla
	 */
	@Override
	public Class<?> getColumnClass(int column) {
		switch(column) {
		case 2:
			return Double.class;
		case 3:
			return Date.class;
		case 4:
			return Date.class;
			default :
				return String.class;
		}
		
	}
	/**
	 * Método que modifca el contenido de la tabla
	 */
	@Override
	public void setValueAt(Object Value, int row, int col) {
		switch(col) {
		case 3:
			data.get(row).setFechaInicio(new java.sql.Date(((java.util.Date)Value).getTime()));
			break;
		case 4:
			data.get(row).setFechaFin(new java.sql.Date(((java.util.Date)Value).getTime()));
		}
		fireTableCellUpdated(row, col);
	}

}
