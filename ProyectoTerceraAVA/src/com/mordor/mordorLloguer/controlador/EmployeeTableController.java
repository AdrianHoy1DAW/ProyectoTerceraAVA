package com.mordor.mordorLloguer.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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
	
	/**
	 * Constructor del controlador
	 * @param vista Vista que utilizará el controlador
	 * @param modelo Modelo que utilizará el controlador
	 */
	public EmployeeTableController(EmployeeTableView vista, AlmacenDatosDB modelo) {
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	/**
	 * Método que inicializa los componentes y anyade el Pop UP Menú
	 */
	private void inicializar() {
		
		vista.getComboBoxDatos().addActionListener(this);
		vista.getComboBoxAsc().addActionListener(this);
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnDelete().addActionListener(this);
		vista.getMntmAdd().addActionListener(this);
		vista.getMntmDelete().addActionListener(this);
		
		vista.getComboBoxDatos().setActionCommand("Ordenar por datos");
		vista.getComboBoxAsc().setActionCommand("Ordenar asc/desc");
		vista.getBtnAdd().setActionCommand("Add employee");
		vista.getBtnDelete().setActionCommand("Delete employee");
		vista.getMntmAdd().setActionCommand("Add employee");
		vista.getMntmDelete().setActionCommand("Delete employee");
		
		vista.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e)  {
				if(e.getButton() == 3) {
					
					int row = vista.getTable().rowAtPoint(e.getPoint());
					if(row < 0 || row > vista.getTable().getRowCount()) {
						vista.getTable().clearSelection();
					} else if(vista.getTable().getSelectedRowCount() <= 1) {
						vista.getTable().setSelectedRow(row);
						vista.getPopupMenu().show(vista.getTable(),e.getX(),e.getY());
						
					} else if(vista.getTable().getSelectedRowCount() > 1) {
						vista.getPopupMenu().show(vista.getTable(), e.getX(), e.getY());
					}
					
				}
			}
		});


		addEmployee = new JIFAddEmployee();
		
		
	}
	/**
	 * Método que se llama desde fuera para rellenar la tabla
	 */
	public void go() {
		rellenarTabla();
	}

	public void rellenarTabla() {
		
		ArrayList<String> header =  new ArrayList<>(Arrays.asList(new String[]{"DNI","NOMBRE","APELLIDOS","DOMICILIO","CP","EMAIL","FECHANAC","CARGO"}));
			
		metm = new MyEmployeeTableModel(empleados,header);
		
	
		swingWorkerOrder("Creating Table");
		
		metm.addTableModelListener(this);
		
	

		
	}
	/**
	 * Método que recibe los eventos de los componentes y les anyade  métodos
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comand = arg0.getActionCommand();
		
		if(comand.equals("Ordenar por datos")) {
			ordenar();
		} else if(comand.equals("Add employee")) {
			addEmployee();
		} else if(comand.equals("Delete employee")) {
			deleteEmployee();
		} else if(comand.equals("Insert Employee")) {
			insertEmployee();
		}
		
	}
	/**
	 * Método que anyade empleados a la tabla
	 */
	private void insertEmployee() {
		
		if(addEmployee.getTextFieldFecha().getDate() != null) {
			Empleado e = null;
			
			
			
			metm.add(e);
		} else {
			JOptionPane.showMessageDialog(vista,"Hay campos obligatorios que no han sido rellenados", "Error",JOptionPane.ERROR_MESSAGE);
		}
			

		
	}
	/**
	 * Método que borra empleados de la tabla
	 */
	private void deleteEmployee() {
		
		int[] seleccionadas = vista.getTable().getSelectedRows();
		ArrayList<Empleado> empleados = new ArrayList<>();
		for(int i = 0; i < seleccionadas.length; i++) {
			
			empleados.add(metm.get(seleccionadas[i]));
					
		}
		
		
		for(int i = 0; i < empleados.size(); i ++) {
			
			metm.delete(empleados.get(i));

		}
			
		
	}
	/**
	 * Método que abre la ventana de anyadir empleados
	 */
	private void addEmployee() {
		
		ControladorPrincipal.addJInternalFrame(addEmployee);
	
		addEmployee.getBtnAddUser().addActionListener(this);
		addEmployee.getBtnCancel().addActionListener(this);
		
		addEmployee.getBtnAddUser().setActionCommand("Insert Employee");
		addEmployee.getBtnCancel().setActionCommand("Cancel");
	}
	/**
	 * Método que se encarga de ordenar la tabla
	 */
	private void ordenar() {
		
		swingWorkerOrder("Ordering Table");
		
		
	}
	/**
	 * Swing Worker que se encarga de ordenar la tabla según los seleccionado en los combobox
	 * @param msg mensaje que se mostrará mientras se ordena la tabla
	 */
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
		
		
		
		public MyEmployeeTableModel(List<Empleado> data, List<String> header) {
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
		
		public void remove(String DNI) {
			
			delete(new Empleado(DNI));
			
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
		
		if(arg0.getType() == TableModelEvent.DELETE) {
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
							
							modelo.deleteEmpleado(get().getDNI());
							metm.getData().remove(get());
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						} catch (ExecutionException e) {
							
							e.printStackTrace();
						}
					}
				}
				
			};
			
			if(ControladorPrincipal.estaAbierto(jif) == true) {
				
			} else {
				jif = new JIFProcess(task,"Deleting Employee");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
			
			
		}
		
		if(arg0.getType() == TableModelEvent.INSERT) {
			
			SwingWorker<Empleado,Void> task = new SwingWorker<Empleado,Void>() {

				@Override
				protected Empleado doInBackground() throws Exception {
					
					Empleado temporal = new Empleado(
							addEmployee.getTextFieldDNI().getText(),
							addEmployee.getTextFieldNombre().getText(),
							addEmployee.getTextFieldApellidos().getText(),
							addEmployee.getTextFieldCP().getText(),
							addEmployee.getTextFieldEmail().getText(),
							new java.sql.Date(addEmployee.getTextFieldFecha().getDate().getTime()),
							String.valueOf(addEmployee.getComboBox().getSelectedItem())
							,addEmployee.getTextFieldDomicilio().getText(),
							String.valueOf(addEmployee.getPasswordField().getPassword()));;
					return temporal;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
						try {
						
							if(modelo.insertEmpleado(get()) == false) {
								
								JOptionPane.showMessageDialog(vista,"No se puede insertar","Error",JOptionPane.ERROR_MESSAGE);
							} else {
								metm.getData().add(get());
								vista.getTable().setModel(metm);
							}
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						} catch (ExecutionException e) {
							
							e.printStackTrace();
						} catch (HeadlessException e) {
							
							JOptionPane.showMessageDialog(vista, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
							
						} catch (Exception e) {
							
							JOptionPane.showMessageDialog(vista,"Plese, fill all the required fields before continue", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			};
			
			if(ControladorPrincipal.estaAbierto(jif) == true) {
				
			} else {
				jif = new JIFProcess(task,"Inserting Employee");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
		}
		
	}
}	
