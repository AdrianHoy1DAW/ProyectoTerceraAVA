package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCarTableModel extends MyVehicleTableModel<Coche> {

	public static MyCarTableModel mctm;
	
	public MyCarTableModel(List<Coche> coches,List<String> header) {
		super(coches, header);
		
		
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
	public void newData(List<Coche> data) {
		super.newData(data);
	}

	
	
}
