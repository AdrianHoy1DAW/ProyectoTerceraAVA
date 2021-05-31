package com.mordor.mordorLloguer.controlador;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.mordor.mordorLloguer.model.AlmacenDatosDB;
import com.mordor.mordorLloguer.model.Camion;

import com.mordor.mordorLloguer.model.Coche;
import com.mordor.mordorLloguer.model.Furgoneta;
import com.mordor.mordorLloguer.model.Microbus;
import com.mordor.mordorLloguer.model.MyBusTableModel;
import com.mordor.mordorLloguer.model.MyCarTableModel;
import com.mordor.mordorLloguer.model.MyTruckTableModel;
import com.mordor.mordorLloguer.model.MyVanTableModel;
import com.mordor.mordorLloguer.model.Vehiculo;
import com.mordor.mordorLloguer.view.JIFProcess;
import com.mordor.mordorLloguer.view.JPVehicle;
import com.mordor.mordorLloguer.view.VehicleView;

public class VehicleTableController implements TableModelListener, DocumentListener {

	private AlmacenDatosDB modelo;
	private VehicleView vista;
	private List<Coche> coches;
	private List<Furgoneta> furgonetas;
	private List<Camion> camiones;
	private List<Microbus> buses;
	private JIFProcess jif;
	
	public VehicleTableController(AlmacenDatosDB modelo, VehicleView vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
		
		inicializar();
	}
	
	private void inicializar() {
		
		vista.getPanelCar().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelCar().getTxtModel().getDocument().addDocumentListener(this);
		
		
		
	}
	
	public void go() {
		rellenarTabla();
	}

	private void rellenarTabla() {
		


		
		
		
		MyCarTableModel.mctm = new MyCarTableModel(coches);
		MyVanTableModel.mvtm = new MyVanTableModel(furgonetas);
		MyTruckTableModel.mttm = new MyTruckTableModel(camiones);
		MyBusTableModel.mbtm = new MyBusTableModel(buses);
		
		
		swingFillTable();
			
		
		
		MyCarTableModel.mctm.addTableModelListener(this);
		MyVanTableModel.mvtm.addTableModelListener(this);
		MyTruckTableModel.mttm.addTableModelListener(this);
		MyBusTableModel.mbtm.addTableModelListener(this); 
	
		
		
	}
	
	private void rellenarComboBox() {

		
		//coches
		rellenarCombo(coches, vista.getPanelCar());
		//camiones
		rellenarCombo(camiones, vista.getPanelTruck());
		//furgonetas
		rellenarCombo(furgonetas, vista.getPanelVan());
		//microbus
		rellenarCombo(buses, vista.getPanelMinibus());
		
		
	}
	
	private void rellenarCombo(List<? extends Vehiculo> vehiculo, JPVehicle jpv) {
		
		Set<String> tmp;
		Vector<String> tm;
		
		tmp = vehiculo.stream().map((e) -> String.valueOf(e.getCarnet()))
				.collect(Collectors.toSet());
		tm = new Vector<>(tmp);
		tm.add(0, "All");
		jpv.getComboBoxLicense().setModel(new DefaultComboBoxModel<String>(tm));
		tmp = vehiculo.stream().map((e) -> String.valueOf(e.getMotor()))
			.collect(Collectors.toSet());
		tm = new Vector<>(tmp);
		tm.add(0,"All");
		jpv.getComboBoxEngine().setModel(new DefaultComboBoxModel<String>(tm));
	}

	private void ordenar() {
		List<Coche> tmp = null; 
		if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
			 tmp = coches.stream().filter((e) -> e.getMatricula().toUpperCase().contains(vista.getPanelCar().getTxtRegistration().getText().toUpperCase()))
					 		.filter((e) -> e.getMarca().toUpperCase().contains(vista.getPanelCar().getTxtModel().getText().toUpperCase()))
							.collect(Collectors.toList());
			 
			 rellenarCombo(tmp, vista.getPanelCar());
		}
		
		MyCarTableModel.mctm = new MyCarTableModel(tmp);
		vista.getPanelCar().getTable().setModel(MyCarTableModel.mctm);
		MyCarTableModel.mctm.addTableModelListener(this);
		
		
	}
	
	private void swingFillTable() {
		SwingWorker<Void,Integer> task = new SwingWorker<Void,Integer>() {

			int i = 0;
			@Override
			protected Void doInBackground() throws Exception {
				
				coches = modelo.getCoche();
				publish(++i);
				furgonetas = modelo.getFurgoneta();
				publish(++i);
				camiones = modelo.getCamion();
				publish(++i);
				buses = modelo.getMicroBus();
				publish(++i);
				
				return null;
				
				
				
				
			
			}
			
			@Override
			protected void process(List<Integer> chunk) {
				
				jif.getLabel().setVisible(false);
				jif.getProgressBar().setIndeterminate(false);
				jif.getLblTexto().setVisible(true);
				jif.getLblTexto().setText("Creating table " + chunk.get(0) + "/4");
				jif.getProgressBar().setValue(chunk.get(0) * 25);
				
				
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
						
						rellenarComboBox();	
						
						
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
		ordenar();
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
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
