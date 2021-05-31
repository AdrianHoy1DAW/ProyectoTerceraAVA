package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyTruckTableModel extends MyVehicleTableModel<Camion>{

	public static MyTruckTableModel mttm;
	
	public MyTruckTableModel(List<Camion> data) {
		super(data);
		
		HEADER.add("NumRuedas");
		HEADER.add("MMA");
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 7:
			return data.get(row).getNumruedas();
		case 8:
			return data.get(row).getMma();

		default: return super.getValueAt(row, col);
		
		}
	}
	
	@Override
	public void newData(List<Camion> data) {
		super.newData(data);
	}	
	
}
