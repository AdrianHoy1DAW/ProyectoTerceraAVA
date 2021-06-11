package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCarTableModel extends MyVehicleTableModel<Coche> {

	
	/**
	 * Constructor del modelo de tabla de coches al que se le pasa por parámetro la lista de coches y anyade los dos encabezados extras
	 * @param coches
	 */
	public MyCarTableModel(List<Coche> coches) {
		super(coches);
		
		HEADER.add("NumPlazas");
		HEADER.add("NumPuertas");
		
		
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
			return data.get(row).getNumpuertas();
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
			data.get(row).setNumpuertas(Integer.parseInt(value.toString()));
			break;
		default: super.setValueAt(value, row, col);
		
		}
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public void newData(List<Coche> data) {
		super.newData(data);
	}

	
	
}
