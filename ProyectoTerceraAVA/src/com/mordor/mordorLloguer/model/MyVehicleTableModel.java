package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oracle.sql.DATE;

public   class MyVehicleTableModel<T extends Vehiculo> extends MyTableModel<T> {

	public MyVehicleTableModel(List<T> data, List<String> header) {
		super(data, new ArrayList<>(Arrays.asList(new String[]{"Matricula","Marca","Color","Motor","Cilindrada","Estado","Carnet"})));
		
		HEADER.addAll(header);
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
			
		case 0:
			return data.get(row).getMatricula();
			
		case 1:
			return data.get(row).getMarca();
			
		case 2:
			return data.get(row).getColor();
		case 3:
			return data.get(row).getMotor();
		case 4:
			return data.get(row).getCilindrada();
		case 5:
			return data.get(row).getEstado();
			
		case 6:
			return data.get(row).getCarnet();
		
			
			
		
		}
		
		
		return null;
	}
	
	public void newData(List<T> data) {
		this.data = data;
		
	}
	

}
