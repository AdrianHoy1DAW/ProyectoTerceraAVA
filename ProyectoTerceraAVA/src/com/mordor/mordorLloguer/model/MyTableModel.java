package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class MyTableModel<T> extends AbstractTableModel {

	protected final String HEADER[];
	
	protected List<T> data;
	
	public MyTableModel(List<T> data, String[] header) {
		this.data = data;
		this.HEADER = header;
	}
	
	@Override
	public int getColumnCount() {
		
		return HEADER.length;
	}

	@Override
	public int getRowCount() {
		
		return data.size();
	}
	
	@Override
	public String getColumnName(int column) {
		
		return HEADER[column];
		
	}
	


	@Override
	public abstract Object getValueAt(int row, int col);


	
	@Override
	public boolean isCellEditable(int row, int column) {
		
		if(column == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	
	
	public T get(int row) {
		if(row < 0 || row >= data.size())
			return null;
		else
			return data.get(row);
	}
	
	public ArrayList<T> get(int[] rows) {
		
		ArrayList<T> empleados = new ArrayList<>();
		
		for(int row : rows) {
			empleados.add(get(row));
		}
		
		return empleados;
	}
	
	public void add(T empleado) {
		
		data.add(empleado);
		fireTableRowsInserted(data.size() -1, data.size() -1);
		
	}
	
	public void delete(T emple) {
		int pos = data.indexOf(emple);	
		fireTableRowsDeleted(pos, pos);
		
		
		
		
	}

	public String[] getHEADER() {
		return HEADER;
	}

	public List<T> getData() {
		return data;
	}
	

	
	
}
