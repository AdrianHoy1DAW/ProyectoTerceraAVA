package com.mordor.mordorLloguer.controlador;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Coche;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.view.JIFProcess;
import com.mordor.mordorLloguer.view.VehicleView;

public class VehicleTableController {

	private AlmacenDatosDB modelo;
	private VehicleView vista;
	private List<Coche> coches;
	private JInternalFrame jif;
	
	public VehicleTableController(AlmacenDatosDB modelo, VehicleView vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
	}
	
	private void inicializar() {
		
		
		
		
		
	}
	
	public void go() {
		rellenarTabla();
	}

	private void rellenarTabla() {
		
		List<String> header = new ArrayList<>(Arrays.asList(new String[] {"Numplazas","Numpuertas"}));
		coches = new ArrayList<>();
		
		MyCarTableModel.mctm = new MyCarTableModel(coches,header);
		
	
		
		
	}
	
	private void swingFillTable(String msg) {
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				coches = modelo.getCoche();
				
				return null;
				
				
				
				
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					try {
						
						MyCarTableModel.mctm.newData(coches);
						
						vista.getPanelCar().getTable().setModel(MyCarTableModel.mctm);
						
						
						
						
						
						if(ControladorPrincipal.estaAbierto(vista)) {
							
						} else 
							ControladorPrincipal.addJInternalFrame(vista);
						
					} catch (Exception e) {
						
					}
					
					
				}
			}
			
			
		};
		
		jif = new JIFProcess(task,"Creating Table");
		ControladorPrincipal.addJInternalFrame(jif);
		task.execute();
	}
	
}
