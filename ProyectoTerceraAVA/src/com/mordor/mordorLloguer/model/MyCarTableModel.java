package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCarTableModel extends MyVehicleTableModel<Coche> {

	
	
	public MyCarTableModel(List<Coche> coches) {
		super(coches);
		
		HEADER.add("NumPlazas");
		HEADER.add("NumPuertas");
		
		
	}
	

	
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
