package com.mordor.mordorLloguer.model;

import java.util.List;

public class MyBusTableModel extends MyVehicleTableModel<Microbus> {
	
	public static MyBusTableModel mbtm;

	public MyBusTableModel(List<Microbus> data, List<String> header) {
		super(data, header);
		// TODO Auto-generated constructor stub
	}

	
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
	
	@Override
	public void newData(List<Microbus> data) {
		super.newData(data);
	}
}
