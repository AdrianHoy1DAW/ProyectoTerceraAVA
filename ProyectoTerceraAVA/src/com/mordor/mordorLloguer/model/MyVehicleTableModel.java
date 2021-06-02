package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oracle.sql.DATE;

public   class MyVehicleTableModel<T extends Vehiculo> extends MyTableModel<T> {

	public MyVehicleTableModel(List<T> data) {
		super(data, new ArrayList<>(Arrays.asList(new String[]{"Matricula","Marca","Color","Motor","Cilindrada","Estado","Carnet"})));
		
		
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
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		switch(col) {
		
		case 1:
			data.get(row).setMarca(value.toString());
			break;
		case 2:
			data.get(row).setColor(value.toString());
			break;
		case 3:
			data.get(row).setMotor(value.toString());
			break;
		case 4:
			data.get(row).setCilindrada(Double.parseDouble(value.toString()));
			break;
		case 5:
			data.get(row).setEstado(value.toString());
			break;
		case 6:
			data.get(row).setCarnet(value.toString().charAt(0));
			break;
		
		}
		fireTableCellUpdated(row, col);
	}
	
	public void newData(List<T> data) {
		this.data = data;
		
	}
	

}
