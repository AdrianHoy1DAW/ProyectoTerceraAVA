package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Alquiler;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Factura;
import com.mordor.mordorLloguer.model.MyInvoiceTableModel;
import com.mordor.mordorLloguer.view.JIFInvoice;
import com.mordor.mordorLloguer.view.JIFProcess;

public class InvoiceController implements ActionListener {
	
	private AlmacenDatosDB modelo;
	private JIFInvoice vista;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private ArrayList<Alquiler> alquileres;
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
		
		vista.getBtnPreviousInvoice().setActionCommand("Previous invoice");
		vista.getBtnNextInvoice().setActionCommand("Next invoice");
		
		
		
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
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
			
		}
		
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
		for(Alquiler a : alquileres) {
			
			if(a.getIdfactura() == facturas.get(indice).getIdfactura()) {
				
				tmp.add(a);
				
			}
			
		}
		
		MyInvoiceTableModel.mitm = new MyInvoiceTableModel(tmp);
		vista.getTableDetalles().setModel(MyInvoiceTableModel.mitm);
	}
	

}
