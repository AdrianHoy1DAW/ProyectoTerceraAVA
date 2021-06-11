package com.mordor.mordorLloguer.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class MyTableModel<T> extends AbstractTableModel {

	protected final  List<String> HEADER;
	
	protected List<T> data;
	/**
	 * Constructor del modelo de tabla
	 * @param data
	 * @param header
	 */
	public MyTableModel(List<T> data, List<String> header) {
		this.data = data;
		this.HEADER = header;
	}
	/**
	 * Método que devuelve el número de columnas
	 */
	@Override
	public int getColumnCount() {
		
		return HEADER.size();
	}
	/**
	 * Métodos que devuelve el número de filas
	 */
	@Override
	public int getRowCount() {
		
		return data.size();
	}
	/**
	 * Método que coge el nombre de la columna
	 */
	@Override
	public String getColumnName(int column) {
		
		return HEADER.get(column);
		
	}
	

	/**
	 * Método abstracto que obliga a los hijos a implementarlos
	 */
	@Override
	public abstract Object getValueAt(int row, int col);


	/**
	 * Método que indica el número de celdas que son editables
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		
		if(column == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	
	/**
	 * Método que coge los datos de una fila concreta
	 * @param row la fila de donde se coge la información
	 * @return el valor devuelto de la fila
	 */
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
	/**
	 * Método que anyade un objeto a la tabla
	 * @param empleado el objeto a insertar
	 */
	public void add(T empleado) {
		
		
		fireTableRowsInserted(data.size() -1, data.size() -1);
		
	}
	/**
	 * Método que borra de la tabla
	 * @param emple objeto a borrar
	 */
	public void delete(T emple) {
		int pos = data.indexOf(emple);	
		fireTableRowsDeleted(pos, pos);
		
		
		
		
	}

	public List<String> getHEADER() {
		return HEADER;
	}

	public List<T> getData() {
		return data;
	}
	

	
	
}
