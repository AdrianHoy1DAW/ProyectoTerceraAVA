package com.mordor.mordorLloguer.model;

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

}
