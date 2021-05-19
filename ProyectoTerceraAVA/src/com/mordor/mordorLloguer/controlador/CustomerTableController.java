package com.mordor.mordorLloguer.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Empleado;
import com.mordor.mordorLloguer.model.MyTableModel;
import com.mordor.mordorLloguer.view.ClientTableView;
import com.mordor.mordorLloguer.view.EmployeeTableView;
import com.mordor.mordorLloguer.view.JIFAddEmployee;
import com.mordor.mordorLloguer.view.JIFProcess;

public class CustomerTableController implements ActionListener,TableModelListener,DocumentListener {

	AlmacenDatosDB modelo;
	ClientTableView vista;
	MyCustomerTableModel mctm;
	ArrayList<Cliente> cliente;
	JInternalFrame jif;
	
	public CustomerTableController(AlmacenDatosDB modelo, ClientTableView vista) {
		
		this.modelo = modelo;
		this.vista = vista;
		
		inicializar();
	}
	
	private void inicializar() {
		
		vista.getBtnDelete().addActionListener(this);
		vista.getTextFieldDni().getDocument().addDocumentListener(this);
		vista.getTextFieldName().getDocument().addDocumentListener(this);
		vista.getTextFieldSur().getDocument().addDocumentListener(this);
		vista.getComboBox().addActionListener(this);
		
		vista.getBtnDelete().setActionCommand("DeleteCustomer");
		vista.getComboBox().setActionCommand("order");
		
		
	}
	
	public void rellenarTabla() {
		String[] header = {"DNI","Nombre","Apellidos","Domicilio","CP","Email","FechaNac","Carnet"};
		 cliente = new ArrayList<Cliente>();
		
		mctm = new MyCustomerTableModel(cliente,header);
		
		swingFillTable();
		
		mctm.addTableModelListener(this);
		
		
	}
	
	public void go() {
		
		rellenarTabla();
		
	}

	private void swingFillTable() {
		SwingWorker<ArrayList<Cliente>,Void> task = new SwingWorker<ArrayList<Cliente>,Void>() {

			@Override
			protected ArrayList<Cliente> doInBackground() throws Exception {
				ArrayList<Cliente> cliente = new ArrayList<>();
				
				
				
					cliente = modelo.getClientes();
				
				
				return cliente;
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					try {
						cliente = get();
						mctm.newData(cliente);
						
						vista.getTable().setModel(mctm);
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
		
		jif = new JIFProcess(task,"Creating Table");
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
	}
	
	
	@Override
	public void tableChanged(TableModelEvent arg0) {
		
		if(arg0.getType() == TableModelEvent.DELETE) {
			SwingWorker<Cliente,Void> task = new SwingWorker<Cliente,Void>() {

				@Override
				protected Cliente doInBackground() throws Exception {
					
					Cliente temporal = mctm.get(arg0.getFirstRow());
					return temporal;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
						try {
							
							modelo.deleteEmpleado(get().getDNI());
							mctm.getData().remove(get());
							
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
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comand = arg0.getActionCommand();
		
		if(comand.equals("DeleteCustomer")) {
			deleteCustomer();
		} else if(comand.equals("order")) {
			ordenar();
		}
		
	}
		
	private void deleteCustomer() {
		
		int[] seleccionadas = vista.getTable().getSelectedRows();
		ArrayList<Cliente> clientes = new ArrayList<>();
		for(int i = 0; i < seleccionadas.length; i++) {
			
			clientes.add(mctm.get(seleccionadas[i]));
					
		}
		
		
		for(int i = 0; i < clientes.size(); i ++) {
			
			mctm.delete(clientes.get(i));

		}
		
	}
	
	private void ordenar() {
		
		List<Cliente> tmp = cliente.stream().filter((e) -> e.getDNI().toUpperCase().contains(vista.getTextFieldDni().getText().toUpperCase()))
				.filter((e) -> e.getNombre().toUpperCase().contains(vista.getTextFieldName().getText().toUpperCase()))
				.filter((e) -> e.getApellidos().toUpperCase().contains(vista.getTextFieldSur().getText().toUpperCase()))
				.filter((e) -> String.valueOf(e.getCarnet()).toUpperCase().contains(String.valueOf(vista.getComboBox().getSelectedItem()).toUpperCase()) || vista.getComboBox().getSelectedItem().toString().equals("All")) 
				.collect(Collectors.toList());
		
		mctm = new MyCustomerTableModel(tmp,mctm.getHEADER());
		vista.getTable().setModel(mctm);
		mctm.addTableModelListener(this);
	}

	private  class MyCustomerTableModel extends MyTableModel<Cliente> {

		
		
		
		
		public MyCustomerTableModel(List<Cliente> data, String[] header) {
			super(data, header);
			
		}
		

		public void newData(List<Cliente> data) {
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
				return data.get(row).getCp();
			case 5:
				return data.get(row).getEmail();
			case 6:
				return data.get(row).getFechaNac();
			case 7:
				return data.get(row).getCarnet();
				
			
			}
			return null;
			
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			
			return false;
			
		}
		
		
		
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		
		ordenar();
		
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		ordenar();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		ordenar();
	}
		
}
