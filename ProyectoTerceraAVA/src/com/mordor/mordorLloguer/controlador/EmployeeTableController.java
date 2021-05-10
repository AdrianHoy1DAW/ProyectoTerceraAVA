package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.model.Empleado;
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
		
		
		
		
		
	}

	public void rellenarTabla() {
		
		String[] header  =  {"DNI","NOMBRE","APELLIDOS","DOMICILIO","CP","EMAIL","FECHANAC","CARGO"};
		ArrayList<Empleado> empleados = modelo.getEmpleados();
		
		
		MyEmployeeTableModel metm = new MyEmployeeTableModel(empleados,header);
		vista.getTable().setModel(metm);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		
	}
	
	private class MyEmployeeTableModel extends MyTableModel<Empleado> {
		
		List<Empleado> data;
		
		public MyEmployeeTableModel(List<Empleado> data, String[] header) {
			super(data, header);
			this.data = data;
			
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 0:
				return data.get(row).getDNI();
				
			case 1:
				return data.get(row).getNombre();
				
			case 2:
				return data.get(row).getApellidos();
			case 3:
				return data.get(row).getDomicilio();
			case 4:
				return data.get(row).getCP();
			case 5:
				return data.get(row).getEmail();
			case 6:
				return data.get(row).getFechaNac();
			case 7:
				return data.get(row).getCargo();
				
			
			}
			return null;
			
		}
		
	}
	
}
