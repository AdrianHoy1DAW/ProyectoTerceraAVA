package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyInvoiceTableModel extends MyTableModel<Alquiler>{

	
	private List<Vehiculo> datas;
	
	public MyInvoiceTableModel(List<Alquiler> data,List<Vehiculo> datas) {
		super(data, new ArrayList<String>(Arrays.asList(new String[]{"Modelo","Matricula","Importe","FechaInicio","FechaFin"})));
		
		this.datas = datas;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 0:
			return datas.get(row).getMarca();
		case 1:
			return datas.get(row).getMatricula();
		case 2:
			return data.get(row).getPrecio();
		case 3:
			return data.get(row).getFechaInicio();
		case 4:
			return data.get(row).getFechaFin();
		
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		
		switch(column) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
			return false;
			default : return true;
		}
		
		
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		switch(column) {
		case 2:
			return Double.class;
		case 3:
			return Date.class;
		case 4:
			return Date.class;
			default :
				return String.class;
		}
		
	}
	
	@Override
	public void setValueAt(Object Value, int row, int col) {
		switch(col) {
		case 3:
			data.get(row).setFechaInicio(new java.sql.Date(((java.util.Date)Value).getTime()));
			break;
		case 4:
			data.get(row).setFechaFin(new java.sql.Date(((java.util.Date)Value).getTime()));
		}
		fireTableCellUpdated(row, col);
	}

}
