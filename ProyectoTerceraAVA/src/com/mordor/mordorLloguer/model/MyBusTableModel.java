package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyBusTableModel extends MyVehicleTableModel<Microbus> {
	
	
	/**
	 * Constructor del modelo al que se le pasa una lista de microbuses los cuales rellenarán la tabla
	 * @param data
	 */
	public MyBusTableModel(List<Microbus> data) {
		super(data);
		
		HEADER.add("Plazas");
		HEADER.add("Medida");
	}

	/**
	 * Método que coge los valores con los que se va a rellenar la tabla llamando al método del padre que rellena el resto
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 7:
			return data.get(row).getNumplazas();
		case 8:
			return data.get(row).getMedida();

		default: return super.getValueAt(row, col);
		
		}
	}
	/**
	 * Método que se encarga de modificar los campos que se modifican en la tabla llamando al método del padre
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		switch(col) {
		
		case 7:
			data.get(row).setNumplazas(Integer.parseInt(value.toString()));
			break;
		case 8:
			data.get(row).setMedida(Integer.parseInt(value.toString()));
			break;
		default: super.setValueAt(value, row, col);
		
		}
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public void newData(List<Microbus> data) {
		super.newData(data);
	}
}
