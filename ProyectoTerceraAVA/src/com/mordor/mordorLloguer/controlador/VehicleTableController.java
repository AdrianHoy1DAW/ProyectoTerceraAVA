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
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import com.mordor.mordorLloguer.view.AddVehicle;
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
	private AddVehicle av;
	
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
		vista.getPanelCar().getBtnDelete().addActionListener(this);
		vista.getPanelCar().getBtnAdd().addActionListener(this);
		
		vista.getPanelTruck().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelTruck().getComboBoxEngine().addActionListener(this);
		vista.getPanelTruck().getComboBoxLicense().addActionListener(this);
		vista.getPanelTruck().getBtnDelete().addActionListener(this);
		vista.getPanelTruck().getBtnAdd().addActionListener(this);
		
		vista.getPanelVan().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelVan().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelVan().getComboBoxEngine().addActionListener(this);
		vista.getPanelVan().getComboBoxLicense().addActionListener(this);
		vista.getPanelVan().getBtnDelete().addActionListener(this);
		vista.getPanelVan().getBtnAdd().addActionListener(this);
		
		vista.getPanelMinibus().getTxtModel().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getTxtRegistration().getDocument().addDocumentListener(this);
		vista.getPanelMinibus().getComboBoxEngine().addActionListener(this);
		vista.getPanelMinibus().getComboBoxLicense().addActionListener(this);
		vista.getPanelMinibus().getBtnDelete().addActionListener(this);
		vista.getPanelMinibus().getBtnAdd().addActionListener(this);
		
		
		vista.getPanelCar().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelCar().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelCar().getBtnDelete().setActionCommand("Delete");
		vista.getPanelTruck().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelTruck().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelTruck().getComboBoxLicense().setActionCommand("Delete");
		vista.getPanelVan().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelVan().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelVan().getBtnDelete().setActionCommand("Delete");
		vista.getPanelMinibus().getComboBoxEngine().setActionCommand("Filtrar");
		vista.getPanelMinibus().getComboBoxLicense().setActionCommand("Filtrar");
		vista.getPanelMinibus().getBtnDelete().setActionCommand("Delete");
		vista.getPanelCar().getBtnAdd().setActionCommand("addVehicle");
		vista.getPanelTruck().getBtnAdd().setActionCommand("addVehicle");
		vista.getPanelVan().getBtnAdd().setActionCommand("addVehicle");
		vista.getPanelMinibus().getBtnAdd().setActionCommand("addVehicle");
		
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
		
		if(e.getType() == TableModelEvent.DELETE) {
			

			SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

				@Override
				protected Void doInBackground() {
					
					try {
						if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
							
							Coche c = coches.get(e.getFirstRow());
							if(modelo.deleteCar(c.getMatricula()) == true ) {
								MyCarTableModel.mctm.getData().remove(c);
							} 
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
							
							Camion t = camiones.get(e.getFirstRow());
							if(modelo.deleteTruck(t.getMatricula()) == true) {
								MyTruckTableModel.mttm.getData().remove(t);
							}
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
							
							Furgoneta f = furgonetas.get(e.getFirstRow());
							if(modelo.deleteVan(f.getMatricula()) == true) {
								MyVanTableModel.mvtm.getData().remove(f);
							}
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
							
							Microbus m = buses.get(e.getFirstRow());
							if(modelo.deleteMinibus(m.getMatricula()) == true) {
								MyBusTableModel.mbtm.getData().remove(m);
							}
							
						}
					} catch(SQLException e) {
						JOptionPane.showMessageDialog(vista, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
					}
					
					return null;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
					
						
						
					}
				}
				
			};
			
			if(ControladorPrincipal.estaAbierto(jif) == true) {
				
			} else {
				jif = new JIFProcess(task,"Deleting Vehicle");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
			
		} else if(e.getType() == TableModelEvent.UPDATE) {
			
			SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

				@Override
				protected Void doInBackground() {
					
					try {
						if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
							
							modelo.updateCar(MyCarTableModel.mctm.get(e.getFirstRow()));
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
							
							modelo.updateTruck(MyTruckTableModel.mttm.get(e.getFirstRow()));
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
							
							modelo.updateVan(MyVanTableModel.mvtm.get(e.getFirstRow()));
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
							
							modelo.updateMinibus(MyBusTableModel.mbtm.get(e.getFirstRow()));
							
						}
					} catch(SQLException e) {
						JOptionPane.showMessageDialog(vista, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
					}
					
					return null;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
					
						
						
					}
				}
				
			};
			
			if(ControladorPrincipal.estaAbierto(jif) == true) {
				
			} else {
				jif = new JIFProcess(task,"Updating Vehicle");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
			
		} else if(e.getType() == TableModelEvent.INSERT) {
			
			SwingWorker<Void,Void> task = new SwingWorker<Void,Void>() {

				@Override
				protected Void doInBackground() {
					
					try {
						if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
							
							if(modelo.addCar(MyCarTableModel.mctm.get(e.getFirstRow())) == true) {
								
								MyCarTableModel.mctm.newData(coches);
								vista.getPanelCar().getTable().setModel(MyCarTableModel.mctm);
								
							} else {
								coches.remove(e.getFirstRow());
							}
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
							
							if(modelo.updateTruck(MyTruckTableModel.mttm.get(e.getFirstRow()))==true) {
								
								MyTruckTableModel.mttm.newData(camiones);
								vista.getPanelTruck().getTable().setModel(MyTruckTableModel.mttm);
								
							} else {
								camiones.remove(e.getFirstRow());	
							}
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
							
							if(modelo.updateVan(MyVanTableModel.mvtm.get(e.getFirstRow()))==true) {
								
								MyVanTableModel.mvtm.newData(furgonetas);
								vista.getPanelVan().getTable().setModel(MyVanTableModel.mvtm);
								
							} else {
								furgonetas.remove(e.getFirstRow());
							}
							
						} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
							
							if(modelo.updateMinibus(MyBusTableModel.mbtm.get(e.getFirstRow()))==true) {
								
								MyBusTableModel.mbtm.newData(buses);
								vista.getPanelMinibus().getTable().setModel(MyBusTableModel.mbtm);
								
							} else {
								buses.remove(e.getFirstRow());
							}
							
						}
					} catch(SQLException e) {
						JOptionPane.showMessageDialog(vista, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
					}
					
					return null;
				}
				
				@Override
				protected void done() {
					jif.dispose();
					
					if(!isCancelled()) {
					
						
						
					}
				}
				
			};
			
			if(ControladorPrincipal.estaAbierto(jif) == true) {
				
			} else {
				jif = new JIFProcess(task,"Updating Vehicle");
				ControladorPrincipal.addJInternalFrame(jif);
			}
			
			task.execute();
			
		}
		
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
		} else if(comand.equals("Delete")) {
			delete();
		} else if(comand.equals("addVehicle")) {
			addVehicle();
		} else if(comand.equals("add")) {
			add();
		}
		 
	}

	private void add() {
		
		if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
			
			Coche c = new Coche(av.getTxtMatricula().getText(),
					Double.parseDouble(av.getTxtPrecio().getText()),av.getTxtMarca().getText(),
					av.getTxtDescripcion().getText(),av.getTxtColor().getText(),
					String.valueOf(av.getComboBoxMotor().getSelectedItem()),
					(av.getTxtCilindrada().getText() != null)? null:Double.parseDouble(av.getTxtCilindrada().getText()),
					new java.sql.Date(av.getTxtFecha().getDate().getTime()),
					String.valueOf(av.getComboBoxEstado().getSelectedItem()),
					String.valueOf(av.getComboBoxCarnet().getSelectedItem()).charAt(0)
					,Integer.parseInt(av.getTxtArriba().getText()),
					Integer.parseInt(av.getTxtAbajo().getText()));
			
			coches.add(c);
			MyCarTableModel.mctm.add(c);
			
			
			
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
			
			Camion c = new Camion(av.getTxtMatricula().getText(),
					Double.parseDouble(av.getTxtPrecio().getText()),av.getTxtMarca().getText(),
					av.getTxtDescripcion().getText(),av.getTxtColor().getText(),
					String.valueOf(av.getComboBoxMotor().getSelectedItem()),
					(av.getTxtCilindrada().getText() == null)? null:Double.parseDouble(av.getTxtCilindrada().getText()),
					new java.sql.Date(av.getTxtFecha().getDate().getTime()),
					String.valueOf(av.getComboBoxEstado().getSelectedItem()),
					String.valueOf(av.getComboBoxCarnet().getSelectedItem()).charAt(0)
					,Integer.parseInt(av.getTxtArriba().getText()),
					Double.parseDouble(av.getTxtAbajo().getText()));
			
					camiones.add(c);
			MyTruckTableModel.mttm.add(c);
			
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
			
			Furgoneta f = new Furgoneta(av.getTxtMatricula().getText(),
					Double.parseDouble(av.getTxtPrecio().getText()),av.getTxtMarca().getText(),
					av.getTxtDescripcion().getText(),av.getTxtColor().getText(),
					String.valueOf(av.getComboBoxMotor().getSelectedItem()),
					(av.getTxtCilindrada().getText() == null)? null:Double.parseDouble(av.getTxtCilindrada().getText()),
					new java.sql.Date(av.getTxtFecha().getDate().getTime()),
					String.valueOf(av.getComboBoxEstado().getSelectedItem()),
					String.valueOf(av.getComboBoxCarnet().getSelectedItem()).charAt(0)
					,Double.parseDouble(av.getTxtArriba().getText()));
			
					furgonetas.add(f);
			MyVanTableModel.mvtm.add(f);
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
			
			Microbus t = new Microbus(av.getTxtMatricula().getText(),
					Double.parseDouble(av.getTxtPrecio().getText()),av.getTxtMarca().getText(),
					av.getTxtDescripcion().getText(),av.getTxtColor().getText(),
					String.valueOf(av.getComboBoxMotor().getSelectedItem()),
					(av.getTxtCilindrada().getText() == null)? null:Double.parseDouble(av.getTxtCilindrada().getText()),
					new java.sql.Date(av.getTxtFecha().getDate().getTime()),
					String.valueOf(av.getComboBoxEstado().getSelectedItem()),
					String.valueOf(av.getComboBoxCarnet().getSelectedItem()).charAt(0)
					,Integer.parseInt(av.getTxtArriba().getText()),
					Double.parseDouble(av.getTxtAbajo().getText()));
			
			buses.add(t);
			
			MyBusTableModel.mbtm.add(t);
			
		}
		
	}

	private void addVehicle() {
		
		if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
			
			av = new AddVehicle("Añadir Coche", "Numplazas","Numpuertas",VehicleView.CAR);
			
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
			
			av = new AddVehicle("Añadir Camion", "Numruedas","MMA",VehicleView.TRUCK);
			
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
			
			av = new AddVehicle("Añadir Furgoneta", "MMA",null,VehicleView.VAN);
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
			
			av = new AddVehicle("Añadir MiniBus", "Numplazas","Medida",VehicleView.MINIBUS);
			
		}
		
		ControladorPrincipal.addJInternalFrame(av);
		av.getBtnAdd().addActionListener(this);
		av.getBtnAdd().setActionCommand("add");
		
	}

	private void delete() {
		
		if(vista.getTabbedPane().getSelectedIndex() == VehicleView.CAR) {
			
			int[] seleccionadas = vista.getPanelCar().getTable().getSelectedRows();
			ArrayList<Coche> coches = new ArrayList<>();
			for(int i = 0; i < seleccionadas.length; i++) {
				
				coches.add(MyCarTableModel.mctm.get(seleccionadas[i]));
						
			}
			
			
			for(int i = 0; i < coches.size(); i ++) {
				
				MyCarTableModel.mctm.delete(coches.get(i));

			}
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.TRUCK) {
			
			int[] seleccionadas = vista.getPanelTruck().getTable().getSelectedRows();
			ArrayList<Camion> coches = new ArrayList<>();
			for(int i = 0; i < seleccionadas.length; i++) {
				
				coches.add(MyTruckTableModel.mttm.get(seleccionadas[i]));
						
			}
			
			
			for(int i = 0; i < coches.size(); i ++) {
				
				MyTruckTableModel.mttm.delete(coches.get(i));

			}
			
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.VAN) {
			
			int[] seleccionadas = vista.getPanelVan().getTable().getSelectedRows();
			ArrayList<Furgoneta> coches = new ArrayList<>();
			for(int i = 0; i < seleccionadas.length; i++) {
				
				coches.add(MyVanTableModel.mvtm.get(seleccionadas[i]));
						
			}
			
			
			for(int i = 0; i < coches.size(); i ++) {
				
				MyVanTableModel.mvtm.delete(coches.get(i));

			}
		} else if(vista.getTabbedPane().getSelectedIndex() == VehicleView.MINIBUS) {
			
			int[] seleccionadas = vista.getPanelMinibus().getTable().getSelectedRows();
			ArrayList<Microbus> coches = new ArrayList<>();
			for(int i = 0; i < seleccionadas.length; i++) {
				
				coches.add(MyBusTableModel.mbtm.get(seleccionadas[i]));
						
			}
			
			
			for(int i = 0; i < coches.size(); i ++) {
				
				MyBusTableModel.mbtm.delete(coches.get(i));

			}
			
		}
		
	}
	
}
