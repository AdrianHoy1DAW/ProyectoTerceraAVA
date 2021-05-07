package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.view.EmployeeTableView;

public class EmployeeTableController implements ActionListener{

	AlmacenDatosDB modelo;
	EmployeeTableView vista;
	
	public EmployeeTableController(EmployeeTableView vista, AlmacenDatosDB modelo) {
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
		vista.getComboBoxDatos().addActionListener(this);
		vista.getComboBoxAsc().addActionListener(this);
		
		vista.getComboBoxDatos().setActionCommand("Ordenar por datos");
		vista.getComboBoxAsc().setActionCommand("Ordenar asc/desc");
		
		rellenarTabla();
		
	}

	private void rellenarTabla() {
		
		String[] header  =  {"DNI","NOMBRE","APELLIDOS","DOMICILIO","CP","EMAIL","FECHANAC","CARGO"};
		ArrayList<Empleado> empleados = modelo.getEmpleados();
		
		MyEmployeeTableModel metm = new MyEmployeeTableModel(empleados,header);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		
	}
	
	private class MyEmployeeTableModel<T> extends MyTableModel {

		
		public MyEmployeeTableModel(List data, String[] header) {
			super(data, header);
			
		}

		@Override
		public Object getValueAt(int row, int col) {
			
			
			return null;
		}
		
	}
	
}
