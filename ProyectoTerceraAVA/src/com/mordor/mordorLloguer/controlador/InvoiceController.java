package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Alquiler;
import com.mordor.mordorLloguer.model.Camion;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Coche;
import com.mordor.mordorLloguer.model.Factura;
import com.mordor.mordorLloguer.model.Furgoneta;
import com.mordor.mordorLloguer.model.Microbus;
import com.mordor.mordorLloguer.model.MyInvoiceTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.view.AddInvoice;
import com.mordor.mordorLloguer.view.JIFAddRental;
import com.mordor.mordorLloguer.view.JIFInvoice;
import com.mordor.mordorLloguer.view.JIFProcess;

public class InvoiceController implements ActionListener {
	
	private AlmacenDatosDB modelo;
	private JIFInvoice vista;
	private AddInvoice JIFai;
	private JIFAddRental JIFar;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Alquiler> alquileres;
	private ArrayList<Coche> coches;
	private ArrayList<Camion> camiones;
	private ArrayList<Furgoneta> furgonetas;
	private ArrayList<Microbus> buses;
	private MyInvoiceTableModel mitm;
	private JIFProcess jif;
	private int indice;
	
	public InvoiceController(AlmacenDatosDB modelo, JIFInvoice vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
		
		inicializar();
	}
	
	
	private void inicializar() {
		
		vista.getBtnPreviousInvoice().addActionListener(this);
		vista.getBtnNextInvoice().addActionListener(this);
		vista.getBtnNewInvoce().addActionListener(this);
		vista.getBtnAddDetail().addActionListener(this);
		
		
		
		vista.getBtnPreviousInvoice().setActionCommand("Previous invoice");
		vista.getBtnNextInvoice().setActionCommand("Next invoice");
		vista.getBtnNewInvoce().setActionCommand("Open Invoice");
		vista.getBtnAddDetail().setActionCommand("Open Rental");
		
	}
	
	public void go() {
		
		rellenarFactura();
		
	}


	private void rellenarFactura() {
		
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() {
				
				
				
				
					try {
						
						facturas = modelo.getFacturas();
						clientes = modelo.getClientes();
						alquileres = modelo.getAlquiler();
						coches = modelo.getCoche();
						camiones = modelo.getCamion();
						furgonetas = modelo.getFurgoneta();
						buses = modelo.getMicroBus();
						
					} catch (SQLException | ParseException e) {
						
						JOptionPane.showMessageDialog(vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
				
				
				return null;
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					
					
					vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(0).getIdfactura()));
					vista.getWebDateFieldFechaFactura().setDate(new java.util.Date(facturas.get(0).getFecha().getTime()));
					colocarClientes();
					colocarAlquiler();
					vista.getBtnPreviousInvoice().setEnabled(false);
					if(indice == facturas.size()) {
						vista.getBtnNextInvoice().setEnabled(false);
					}
					
					if(ControladorPrincipal.estaAbierto(vista)) {
						
					} else 
						ControladorPrincipal.addJInternalFrame(vista);
					
					
				}
			}
			
			
		};
		
		jif = new JIFProcess(task,"Creating Table");
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comand = e.getActionCommand();
		
		if(comand.equals("Previous invoice")) {
			
			previousinvoice();
			
		} else if(comand.equals("Next invoice")) {
			
			nextinvoice();
			
		} else if(comand.equals("Open Invoice")) {
			openInvoice();
		} else if(comand.equals("Add Invoice")) {
			addInvoice();
		} else if(comand.equals("Open Rental")) {
			openRental();
		} else if(comand.equals("Add Rental")) {
			addRental();
		}
		
	}


	private void addRental() {
		
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() {
				
				
				
				
					try {
						
						modelo.insertarAlquiler(Integer.parseInt(JIFar.getTxtFactura().getText()),JIFar.getTxtDni().getText(), JIFar.getTxtMatricula().getText(), 
								new java.sql.Date(JIFar.getTxtInicio().getDate().getTime()),new java.sql.Date(JIFar.getTxtFinal().getDate().getTime()));
						
						facturas = modelo.getFacturas();
						alquileres = modelo.getAlquiler();
						
					} catch (SQLException e) {
						
						JOptionPane.showMessageDialog(vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
				
				
				return null;
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					
					
					vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(0).getIdfactura()));
					vista.getWebDateFieldFechaFactura().setDate(new java.util.Date(facturas.get(0).getFecha().getTime()));
					colocarClientes();
					colocarAlquiler();
					vista.getBtnPreviousInvoice().setEnabled(false);
					if(indice == facturas.size()) {
						vista.getBtnNextInvoice().setEnabled(false);
					}
					
					if(ControladorPrincipal.estaAbierto(vista)) {
						
					} else 
						ControladorPrincipal.addJInternalFrame(vista);
					
					
				}
			}
			
			
		};
		
		jif = new JIFProcess(task,"Creating Table");
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
		
		
		
	}


	private void openRental() {
		
		JIFar = new JIFAddRental();
		ControladorPrincipal.addJInternalFrame(JIFar);
		JIFar.getBtnInsertar().addActionListener(this);
		JIFar.getBtnInsertar().setActionCommand("Add Rental");
		JIFar.getTxtFactura().setText(String.valueOf(facturas.get(indice).getIdfactura()));
		JIFar.getTxtDni().setText(vista.getTxtFieldDNI().getText());
		JIFar.getTxtFactura().setEnabled(false);
		JIFar.getTxtDni().setEnabled(false);
		
	}


	private void addInvoice() {
		
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() {
				
				
				
				
					try {
						
						modelo.insertarFactura(JIFai.getTxtDni().getText(), JIFai.getTxtMatricula().getText(), 
								new java.sql.Date(JIFai.getTxtInicio().getDate().getTime()),new java.sql.Date(JIFai.getTxtFinal().getDate().getTime()));
						
						facturas = modelo.getFacturas();
						alquileres = modelo.getAlquiler();
						
					} catch (SQLException e) {
						
						JOptionPane.showMessageDialog(vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						
					}
					
				
				
				return null;
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					
					
					vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(0).getIdfactura()));
					vista.getWebDateFieldFechaFactura().setDate(new java.util.Date(facturas.get(0).getFecha().getTime()));
					colocarClientes();
					colocarAlquiler();
					vista.getBtnPreviousInvoice().setEnabled(false);
					if(indice == facturas.size()) {
						vista.getBtnNextInvoice().setEnabled(false);
					}
					
					if(ControladorPrincipal.estaAbierto(vista)) {
						
					} else 
						ControladorPrincipal.addJInternalFrame(vista);
					
					
				}
			}
			
			
		};
		
		jif = new JIFProcess(task,"Creating Table");
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
		
	}


	private void openInvoice() {
		
		JIFai = new AddInvoice();
		ControladorPrincipal.addJInternalFrame(JIFai);
		JIFai.getBtnInsertar().addActionListener(this);
		JIFai.getBtnInsertar().setActionCommand("Add Invoice");
		
	}


	private void nextinvoice() {
		
		vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(++indice).getIdfactura()));
		vista.getWebDateFieldFechaFactura().setDate(new java.util.Date(facturas.get(indice).getFecha().getTime()));
		colocarClientes();
		colocarAlquiler();
		 if(indice == facturas.size() -1) {
			 vista.getBtnNextInvoice().setEnabled(false);
		 }
		 vista.getBtnPreviousInvoice().setEnabled(true);
		
	}


	private void previousinvoice() {
		
		vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(--indice).getIdfactura()));
		vista.getWebDateFieldFechaFactura().setDate(new java.util.Date(facturas.get(indice).getFecha().getTime()));
		colocarClientes();
		colocarAlquiler();
		 if(indice == 0) {
			 vista.getBtnPreviousInvoice().setEnabled(false);
		 }
		 vista.getBtnNextInvoice().setEnabled(true);
		
	}
	
	private void colocarClientes() {
		
		for(Cliente c : clientes) {
			
			if(c.getIdcliente() == facturas.get(indice).getClienteid()) {
				vista.getTxtFieldNombre().setText(c.getNombre());
				vista.getTxtFieldApellidos().setText(c.getApellidos());
				vista.getTxtFieldDNI().setText(c.getDNI());
			}
			
		}
		
	}
	
	private void colocarAlquiler() {
		
		ArrayList<Alquiler> tmp = new ArrayList<>();
		ArrayList<Vehiculo> tv = new ArrayList<>(coches);
		ArrayList<Vehiculo> tmpvehi = new ArrayList<>();
		tv.addAll(camiones);
		tv.addAll(furgonetas);
		tv.addAll(buses);
		
		for(Alquiler a : alquileres) {
			
			if(a.getIdfactura() == facturas.get(indice).getIdfactura()) {
				
				tmp.add(a);
				
			}
			
		}
		
		for(int i = 0;i < tmp.size(); i++ ) {
			for(Vehiculo v : tv) {
				
				if(v.getMatricula().equals(tmp.get(i).getMatricula())) {
					tmpvehi.add(v);
				}
				
			}
		}

		
		mitm = new MyInvoiceTableModel(tmp,tmpvehi);
		vista.getTableDetalles().setModel(mitm);
	}
	

}
