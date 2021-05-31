package com.mordor.mordorLloguer.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VehicleTableController implements TableModelListener, DocumentListener, ActionListener {

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
		vista.getPanelCar().getComboBoxEngine().addActionListener(this);
		vista.getPanelCar().getComboBoxLicense().addActionListener(this);
		
		vista.getPanelTruck().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getComboBoxEngine().addActionListener(this);
		vista.getPanelTruck().getComboBoxLicense().addActionListener(this);
		
		vista.getPanelVan().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelVan().getComboBoxEngine().addActionListener(this);
		vista.getPanelVan().getComboBoxLicense().addActionListener(this);
		
		vista.getPanelMinibus().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getComboBoxEngine().addActionListener(this);
		vista.getPanelMinibus().getComboBoxLicense().addActionListener(this);
		
		
		vista.getPanelCar().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelCar().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelTruck().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelTruck().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelVan().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelVan().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelMinibus().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelMinibus().getComboBoxLicense().setActionCommand("Filtrar");
		
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
		List<? extends Vehiculo> tmp = null; 
		
		if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {

			tmp = filtrar(coches,vista.getPanelCar());
			MyCarTableModel.mctm = new MyCarTableModel((List<Coche>) tmp);
			vista.getPanelCar().getTable().setModel(MyCarTableModel.mctm);
			MyCarTableModel.mctm.addTableModelListener(this);
			 
		}else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
		
			tmp = filtrar(camiones,vista.getPanelTruck());
			MyTruckTableModel.mttm = new MyTruckTableModel((List<Camion>) tmp);
			vista.getPanelTruck().getTable().setModel(MyTruckTableModel.mttm);
			MyTruckTableModel.mttm.addTableModelListener(this);
			
		}else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
			
			tmp = filtrar(furgonetas,vista.getPanelVan());
			MyVanTableModel.mvtm = new MyVanTableModel((List<Furgoneta>) tmp);
			vista.getPanelVan().getTable().setModel(MyVanTableModel.mvtm );
			MyVanTableModel.mvtm.addTableModelListener(this);
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
			
			tmp = filtrar(buses,vista.getPanelMinibus());
			MyBusTableModel.mbtm = new MyBusTableModel((List<Microbus>) tmp);
			vista.getPanelMinibus().getTable().setModel(MyBusTableModel.mbtm );
			MyBusTableModel.mbtm.addTableModelListener(this);
			
		}
		

		
		
	}
	
	private List<? extends Vehiculo> filtrar(List<? extends Vehiculo> lista, JPVehicle jpv) {
		List<? extends Vehiculo> tmp = null; 
		
		 tmp = lista.stream().filter((e) -> e.getMatricula().toUpperCase().contains(jpv.getTxtRegistration().getText().toUpperCase()))
			 		.filter((e) -> e.getMarca().toUpperCase().contains(jpv.getTxtModel().getText().toUpperCase()))
			 		.filter((e) -> e.getMotor().toUpperCase().contains(String.valueOf(jpv.getComboBoxEngine().getSelectedItem()).toUpperCase()) || jpv.getComboBoxEngine().getSelectedItem().toString().equals("All"))
					.filter((e) -> String.valueOf(e.getCarnet()).toUpperCase().contains(String.valueOf(jpv.getComboBoxLicense().getSelectedItem()).toUpperCase()) || jpv.getComboBoxLicense().getSelectedItem().toString().equals("All"))
			 		.collect(Collectors.toList());
	 
	 
	 String dato = String.valueOf(jpv.getComboBoxEngine().getSelectedItem());
	 String datoLicense = String.valueOf(jpv.getComboBoxLicense().getSelectedItem());
	 
	 rellenarCombo(tmp, jpv);
	 jpv.getComboBoxEngine().setSelectedItem(dato);
	 jpv.getComboBoxLicense().setSelectedItem(datoLicense);
		
	 return tmp;
		
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comand = arg0.getActionCommand();
		
		if(comand.equals("Filtrar")) {
			ordenar();
		}
		
	}
	
}
