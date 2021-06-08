package com.mordor.mordorLloguer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyInvoiceTableModel extends MyTableModel<Alquiler>{

	public static MyInvoiceTableModel mitm;
	
	public MyInvoiceTableModel(List<Alquiler> data) {
		super(data, new ArrayList<String>(Arrays.asList(new String[]{"Importe","FechaInicio","FechaFin"})));
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		
		case 0:
			return data.get(row).getPrecio();
		case 1:
			return data.get(row).getFechaInicio();
		case 2:
			return data.get(row).getFechaFin();
		
		}
		return null;
	}

}
