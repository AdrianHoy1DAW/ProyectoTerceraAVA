package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class VehicleView extends JInternalFrame {



	/**
	 * Create the frame.
	 */
	public VehicleView() {
		setClosable(true);
		setBounds(100, 100, 645, 529);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelCar = new JPVehicle();
		tabbedPane.addTab("Car", null, panelCar, null);
		
		JPanel panelVan = new JPVehicle();
		tabbedPane.addTab("Van", null, panelVan, null);
		
		JPanel panelTruck = new JPVehicle();
		tabbedPane.addTab("Truck", null, panelTruck, null);
		
		JPanel panelMinibus = new JPVehicle();
		tabbedPane.addTab("Minibus", null, panelMinibus, null);

	}

}
