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

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton buttonnLogout;
	private JButton buttonClient;
	private JButton buttonLogin;
	private JMenuItem mntmPreferences;
	private JMenuItem mntmExit;



	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 499);
		
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
		toolBar.add(buttonLogin);
		
		buttonnLogout = new JButton("");
		toolBar.add(buttonnLogout);
		
		buttonClient = new JButton("");
		toolBar.add(buttonClient);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

}
