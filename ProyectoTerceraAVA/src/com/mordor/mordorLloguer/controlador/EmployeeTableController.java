package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.alee.laf.table.editors.WebDateEditor;
import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.view.EmployeeTableView;
import com.mordor.mordorLloguer.view.JIFAddEmployee;
import com.mordor.mordorLloguer.view.JIFProcess;

public class EmployeeTableController implements ActionListener,TableModelListener{

	AlmacenDatosDB modelo;
	EmployeeTableView vista;
	ArrayList<Empleado> empleados;
	MyEmployeeTableModel metm;
	JIFAddEmployee addEmployee;
	
	JInternalFrame jif;
	
	
	public EmployeeTableController(EmployeeTableView vista, AlmacenDatosDB modelo) {
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
		vista.getComboBoxDatos().addActionListener(this);
		vista.getComboBoxAsc().addActionListener(this);
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		
		vista.getComboBoxDatos().setActionCommand("Ordenar por datos");
		vista.getComboBoxAsc().setActionCommand("Ordenar asc/desc");
		vista.getBtnAdd().setActionCommand("Add employee");
		vista.getBtnDelete().setActionCommand("Delete employee");


		addEmployee = new JIFAddEmployee();
		
		
	}
	
	public void go() {
		rellenarTabla();
	}

	public void rellenarTabla() {
		
		String[] header  =  {"DNI","NOMBRE","APELLIDOS","DOMICILIO","CP","EMAIL","FECHANAC","CARGO"};
			
		metm = new MyEmployeeTableModel(empleados,header);
		
	
		swingWorkerOrder("Creating Table");
		
		metm.addTableModelListener(this);
		
	

		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comand = arg0.getActionCommand();
		
		if(comand.equals("Ordenar por datos")) {
			ordenar();
		} else if(comand.equals("Add employee")) {
			addEmployee();
		}
		
	}
	
	private void addEmployee() {
		
		ControladorPrincipal.addJInternalFrame(addEmployee);
		
	}

	private void ordenar() {
		
		swingWorkerOrder("Ordering Table");
		
		
	}

	private void swingWorkerOrder(String msg) {
		SwingWorker<ArrayList<Empleado>,Void> task = new SwingWorker<ArrayList<Empleado>,Void>() {

			@Override
			protected ArrayList<Empleado> doInBackground() throws Exception {
				ArrayList<Empleado> empleado = new ArrayList<>();
				
				if(String.valueOf(vista.getComboBoxAsc().getSelectedItem()) == "Ascendente") {
					empleado = modelo.getEmpleadosOrder(String.valueOf(vista.getComboBoxDatos().getSelectedItem()), AlmacenDatosDB.ASCENDENTE);
				} else {
					empleado = modelo.getEmpleadosOrder(String.valueOf(vista.getComboBoxDatos().getSelectedItem()), AlmacenDatosDB.DESCENDENTE);
				}
				
				return empleado;
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					try {
						empleados = get();
						metm.newData(empleados);
						
						vista.getTable().setModel(metm);
						if(ControladorPrincipal.estaAbierto(vista)) {
							
						} else 
							ControladorPrincipal.addJInternalFrame(vista);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
			
			
		};
		
		jif = new JIFProcess(task,msg);
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
		
	}

	private class MyEmployeeTableModel extends MyTableModel<Empleado> {
		
		
		
		public MyEmployeeTableModel(List<Empleado> data, String[] header) {
			super(data, header);
			
			
		}
		
		public void newData(List<Empleado> data) {
			super.data = data;
			
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
		
		@Override
		public Class<?> getColumnClass(int column) {
			
			switch(column) {
			case 6: 
				return Date.class;
			default : 
				return String.class;
			}
			
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			
			if(column == 0) {
				return false;
			} else {
				return true;
			}
			
		}
		
		@Override
		public void setValueAt(Object Value, int row, int col) {
			switch(col) {
	
			case 0:
				data.get(row).setDNI(Value.toString());
				break;
			case 1:
				data.get(row).setNombre(Value.toString());
				break;
			case 2:
				data.get(row).setApellidos(Value.toString());
				break;
			case 3:
				data.get(row).setDomicilio(Value.toString());
				break;
			case 4:
				data.get(row).setCP(Value.toString());
				break;
			case 5:
				data.get(row).setEmail(Value.toString());
				break;
			case 6:
				data.get(row).setFechaNac(new java.sql.Date(((java.util.Date)Value).getTime()));
				break;
			case 7:
				data.get(row).setCargo(Value.toString());
				break;
			}
			fireTableCellUpdated(row, col);
		
		
	}
		
		public String[] getHeader() {
			return super.HEADER;
		}
	
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		
		if(arg0.getType() == TableModelEvent.UPDATE) {
			SwingWorker<Empleado,Void> task = new SwingWorker<Empleado,Void>() {

				@Override
				protected Empleado doInBackground() throws Exception {
					Empleado temporal = metm.get(arg0.getFirstRow());
					return temporal;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
						try {
							modelo.updateEmpleado(get());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			};
			jif = new JIFProcess(task,"Updating Employee");
			ControladorPrincipal.addJInternalFrame(jif);
			task.execute();
		}
		
	}
}	
