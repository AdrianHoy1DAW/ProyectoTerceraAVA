package com.mordor.mordorLloguer.controlador;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Camion;
import com.mordor.mordorLloguer.model.Cliente;
import com.mordor.mordorLloguer.model.Coche;
import com.mordor.mordorLloguer.model.Furgoneta;
import com.mordor.mordorLloguer.model.Microbus;
import com.mordor.mordorLloguer.model.MyBusTableModel;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.MyTruckTableModel;
import com.mordor.mordorLloguer.model.MyVanTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.view.JIFProcess;
import com.mordor.mordorLloguer.view.VehicleView;

public class VehicleTableController implements TableModelListener {

	private AlmacenDatosDB modelo;
	private VehicleView vista;
	private List<Coche> coches;
	private List<Furgoneta> furgonetas;
	private List<Camion> camiones;
	private List<Microbus> buses;
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
		List<String> headerf = new ArrayList<>(Arrays.asList(new String[] {"Mma"}));
		List<String> headert = new ArrayList<>(Arrays.asList(new String[] {"NumRuedas","Mma"}));
		List<String> headerm = new ArrayList<>(Arrays.asList(new String[] {"Plazas","Medida"}));

		
		
		
		MyCarTableModel.mctm = new MyCarTableModel(coches,header);
		MyVanTableModel.mvtm = new MyVanTableModel(furgonetas,headerf);
		MyTruckTableModel.mttm = new MyTruckTableModel(camiones,headert);
		MyBusTableModel.mbtm = new MyBusTableModel(buses,headerm);
		
		swingFillTable();
		
		MyCarTableModel.mctm.addTableModelListener(this);
		MyVanTableModel.mvtm.addTableModelListener(this);
		MyTruckTableModel.mttm.addTableModelListener(this);
		MyBusTableModel.mbtm.addTableModelListener(this); 
	
		
		
	}
	
	private void swingFillTable() {
		SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				
				coches = modelo.getCoche();
				furgonetas = modelo.getFurgoneta();
				camiones = modelo.getCamion();
				buses = modelo.getMicroBus();
				
				
				return null;
				
				
				
				
			
			}
			@Override
			protected void done() {
				jif.dispose();
				
				if(!isCancelled() ) {
					try {
						
						
						MyCarTableModel.mctm.newData(coches);
						MyVanTableModel.mvtm.newData(furgonetas);
						MyTruckTableModel.mttm.newData(camiones);
						MyBusTableModel.mbtm.newData(buses); 
						
						
						vista.getPanelCar().getTable().setModel(MyCarTableModel.mctm);
						vista.getPanelVan().getTable().setModel(MyVanTableModel.mvtm);
						vista.getPanelTruck().getTable().setModel(MyTruckTableModel.mttm);
						vista.getPanelMinibus().getTable().setModel(MyBusTableModel.mbtm);
						
						
						
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

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
