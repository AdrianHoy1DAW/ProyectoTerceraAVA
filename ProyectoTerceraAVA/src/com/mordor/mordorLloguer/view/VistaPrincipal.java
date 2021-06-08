package com.mordor.mordorLloguer.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton buttonnLogout;
	private JButton buttonClient;
	private JButton buttonLogin;
	private JMenuItem mntmPreferences;
	private JMenuItem mntmExit;
	private JDesktopPane desktopPane;
	private JButton btnCustomer;
	private JButton buttonVehicle;
	private JButton btnFactura;



	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 1000);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.putClientProperty("styleId", "attached-north");
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		buttonLogin = new JButton("");
		buttonLogin.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/login.png")));
		toolBar.add(buttonLogin);
		
		buttonnLogout = new JButton("");
		buttonnLogout.setEnabled(false);
		buttonnLogout.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/logout.png")));
		toolBar.add(buttonnLogout);
		
		buttonClient = new JButton("");
		buttonClient.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/badge.png")));
		toolBar.add(buttonClient);
		
		btnCustomer = new JButton("");
		btnCustomer.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/grupo.png")));
		toolBar.add(btnCustomer);
		
		buttonVehicle = new JButton("");
		buttonVehicle.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/com/mordor/mordorLloguer/assets/car.png")));
		toolBar.add(buttonVehicle);
		
		btnFactura = new JButton("");
		toolBar.add(btnFactura);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}



	public JPanel getContentPane() {
		return contentPane;
	}



	public JButton getButtonnLogout() {
		return buttonnLogout;
	}



	public JButton getButtonClient() {
		return buttonClient;
	}



	public JButton getButtonLogin() {
		return buttonLogin;
	}



	public JMenuItem getMntmPreferences() {
		return mntmPreferences;
	}



	public JMenuItem getMntmExit() {
		return mntmExit;
	}



	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}



	public JButton getBtnCustomer() {
		return btnCustomer;
	}



	public JButton getButtonVehicle() {
		return buttonVehicle;
	}



	public JButton getBtnFactura() {
		return btnFactura;
	}
	
	
	
	
}
