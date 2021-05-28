package com.mordor.mordorLloguer.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
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
import com.mordor.mordorLloguer.view.AddModifyCustomer;
import com.mordor.mordorLloguer.view.ClientTableView;
import com.mordor.mordorLloguer.view.EmployeeTableView;
import com.mordor.mordorLloguer.view.JIFAddEmployee;
import com.mordor.mordorLloguer.view.JIFProcess;

public class CustomerTableController implements ActionListener,TableModelListener,DocumentListener,MouseListener {

	AlmacenDatosDB modelo;
	ClientTableView vista;
	MyCustomerTableModel mctm;
	ArrayList<Cliente> cliente;
	JInternalFrame jif;
	AddModifyCustomer amc;
	
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
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnEdit().addActionListener(this);
		
		vista.getBtnDelete().setActionCommand("DeleteCustomer");
		vista.getComboBox().setActionCommand("order");
		vista.getBtnAdd().setActionCommand("Open add menu");
		vista.getBtnEdit().setActionCommand("Open Edit Menu");
		
		
		
	}
	
	public void rellenarTabla() {
		ArrayList<String> header =  new ArrayList<>(Arrays.asList(new String[]{"DNI","Nombre","Apellidos","Domicilio","CP","Email","FechaNac","Carnet"}));
		 cliente = new ArrayList<Cliente>();
		
		mctm = new MyCustomerTableModel(cliente,header);
		
		swingFillTable("Creating table");
		
		mctm.addTableModelListener(this);
		
		
	}
	
	private void buscarFichero() {
		
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(amc);
		
		try(InputStream inte = new FileInputStream(file.getSelectedFile())) {
			
			byte[] imgFoto = new byte[(int)file.getSelectedFile().length()];
			inte.read(imgFoto);
			amc.setImage(imgFoto);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void go() {
		
		rellenarTabla();
		
	}

	private void swingFillTable(String msg) {
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
							
							if(modelo.deleteCustomer(get().getDNI()) == true) 
								mctm.getData().remove(get());
							else
								JOptionPane.showMessageDialog(vista,"No se puede borrar","Error",JOptionPane.ERROR_MESSAGE);
							
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
				jif = new JIFProcess(task,"Deleting Customer");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
			
			
		}
		
		if(arg0.getType() == TableModelEvent.INSERT) {
			
			SwingWorker<Cliente,Void> task = new SwingWorker<Cliente,Void>() {

				@Override
				protected Cliente doInBackground() throws Exception {
					
					Cliente temporal = new Cliente(
							amc.getTxtDni().getText(),
							amc.getTxtName().getText(),
							amc.getTxtSurname().getText(),
							amc.getTxtAddress().getText(),
							amc.getTxtCp().getText(),
							amc.getTxtEmail().getText(),
							new java.sql.Date(amc.getTxtBirthday().getDate().getTime()),
							String.valueOf(amc.getComboBox().getSelectedItem()).charAt(0),
							amc.getImage());
							
					return temporal;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
						try {
						
							if(modelo.grabarCliente(get()) == false) {
								
								JOptionPane.showMessageDialog(vista,"No se puede insertar","Error",JOptionPane.ERROR_MESSAGE);
							} else {
								if(mctm.getData().contains(get())) {
									swingFillTable("Updating Table");
									vista.getTable().setModel(mctm);
									
								} else {
									mctm.getData().add(get());
									vista.getTable().setModel(mctm);
								}
								
								
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comand = arg0.getActionCommand();
		
		if(comand.equals("DeleteCustomer")) {
			deleteCustomer();
		} else if(comand.equals("order")) {
			ordenar();
		} else if(comand.equals("Open add menu")) {
			addMenu();
		} else if(comand.equals("Add Customer")) {
			addCustomer();
		} else if(comand.equals("Open Edit Menu")) {
			editMenu();
		}
		 
	}
		
	private void editMenu() {
		
		amc = new AddModifyCustomer("Edit");
		
		if(vista.getTable().getSelectedRowCount() == 0) {
			
		} else if(!ControladorPrincipal.estaAbierto(amc)) {
			ControladorPrincipal.addJInternalFrame(amc);
			Cliente temp = mctm.get(vista.getTable().getSelectedRow());
			amc.getTxtDni().setText(temp.getDNI());
			amc.getTxtName().setText(temp.getNombre());
			amc.getTxtSurname().setText(temp.getApellidos());
			amc.getTxtAddress().setText(temp.getDomicilio());
			amc.getTxtCp().setText(temp.getCp());
			amc.getTxtEmail().setText(temp.getEmail());
			amc.getTxtBirthday().setDate(new java.util.Date(temp.getFechaNac().getTime()));
			amc.getComboBox().setSelectedItem(String.valueOf(temp.getCarnet()));
			amc.setImage(temp.getFoto());
			
			amc.getBtnAdd().addActionListener(this);
			amc.getLabelImg().addMouseListener(this);
			amc.getBtnAdd().setActionCommand("Add Customer");
		}
		
		

		
		
	}

	private void addCustomer() {
		
		if(amc.getTxtBirthday().getDate() != null) {
			Cliente c = null;
			
			
			mctm.add(c);
		} else {
			JOptionPane.showMessageDialog(vista,"Hay campos obligatorios que no han sido rellenados", "Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void addMenu() {
		
		amc = new AddModifyCustomer("Add");
		if(!ControladorPrincipal.estaAbierto(amc)) {
			ControladorPrincipal.addJInternalFrame(amc);
		}
		
		amc.getLabelImg().addMouseListener(this);
		amc.getBtnAdd().addActionListener(this);
		
		amc.getBtnAdd().setActionCommand("Add Customer");
		
		
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

		
		
		
		
		public MyCustomerTableModel(List<Cliente> data, List<String> header) {
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		buscarFichero();
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
}
