package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import com.alee.laf.table.WebTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class VehicleView extends JInternalFrame {
	private JPVehicle panelMinibus;
	private JPVehicle panelTruck;
	private JPVehicle panelVan;
	private JPVehicle panelCar;
	private JTabbedPane tabbedPane;
	



	/**
	 * Create the frame.
	 */
	public VehicleView() {
		setClosable(true);
		setBounds(100, 100, 645, 529);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelCar = new JPVehicle();
		tabbedPane.addTab("Car", null, panelCar, null);
		
		panelVan = new JPVehicle();
		tabbedPane.addTab("Van", null, panelVan, null);
		
		panelTruck = new JPVehicle();
		tabbedPane.addTab("Truck", null, panelTruck, null);
		
		panelMinibus = new JPVehicle();
		tabbedPane.addTab("Minibus", null, panelMinibus, null);

	}



	public JPVehicle getPanelMinibus() {
		return panelMinibus;
	}



	public JPVehicle getPanelTruck() {
		return panelTruck;
	}



	public JPVehicle getPanelVan() {
		return panelVan;
	}



	public JPVehicle getPanelCar() {
		return panelCar;
	}



	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	


	
}
