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

import com.mordor.mordorLloguer.view.JIFProcess;
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
		
		Set<String> tmp = coches.stream().map((e) -> String.valueOf(e.getCarnet()))
							.collect(Collectors.toSet());
		Vector<String> tm = new Vector<>(tmp);
		vista.getPanelCar().getComboBoxLicense().setModel(new DefaultComboBoxModel<String>(tm));
		
	}

	private void ordenar() {
		List<Coche> tmp = null; 
		if(vista.getTabbedPane().getSelectedIndex() == 0) {
			 tmp = coches.stream().filter((e) -> e.getMatricula().toUpperCase().contains(vista.getPanelCar().getTxtRegistration().getText().toUpperCase()))
							.collect(Collectors.toList());
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
