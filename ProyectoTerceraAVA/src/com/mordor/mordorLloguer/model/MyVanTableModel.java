package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyVanTableModel extends MyVehicleTableModel<Furgoneta> {

	public static MyVanTableModel mvtm;
	
	public MyVanTableModel(List<Furgoneta> data, List<String> header) {
		super(data, header);
		
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
	public void newData(List<Furgoneta> data) {
		super.newData(data);
	}
	
}
