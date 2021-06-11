package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyVanTableModel extends MyVehicleTableModel<Furgoneta> {


	
	public MyVanTableModel(List<Furgoneta> data) {
		super(data);
		
		HEADER.add("MMA");
		
	}

	
	

	

	
	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 7:
			return data.get(row).getMma();

		default: return super.getValueAt(row, col);
		
		}
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		switch(col) {
		
		case 7:
			data.get(row).setMma(Double.parseDouble(value.toString()));
			break;
		default: super.setValueAt(value, row, col);
		
		}
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public void newData(List<Furgoneta> data) {
		super.newData(data);
	}
	
}
