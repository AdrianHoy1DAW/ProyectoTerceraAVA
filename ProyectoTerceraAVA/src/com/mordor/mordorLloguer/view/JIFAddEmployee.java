package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.alee.extended.date.WebDateField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class JIFAddEmployee extends JInternalFrame {
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDomicilio;
	private JTextField textFieldCP;
	private JLabel lblFechaNacimiento;
	private WebDateField textFieldFecha;
	private JLabel lblCargo;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnAddUser;
	private JButton btnCancel;
	private JComboBox comboBox;



	/**
	 * Create the frame.
	 */
	public JIFAddEmployee() {
		setFrameIcon(new ImageIcon(JIFAddEmployee.class.getResource("/com/mordor/mordorLloguer/assets/useraddalt.png")));
		setClosable(true);
		setBounds(100, 100, 647, 244);
		getContentPane().setLayout(new MigLayout("", "[][181.00][][][grow][]", "[][][][][][][][][]"));
		
		JLabel lblDni = new JLabel("DNI");
		getContentPane().add(lblDni, "cell 0 1,alignx left");
		
		textFieldDNI = new JTextField();
		getContentPane().add(textFieldDNI, "cell 1 1,growx");
		textFieldDNI.setColumns(10);
		
		JLabel lblCp = new JLabel("CP");
		getContentPane().add(lblCp, "cell 3 1,alignx left");
		
		textFieldCP = new JTextField();
		getContentPane().add(textFieldCP, "cell 4 1,growx");
		textFieldCP.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		getContentPane().add(lblNombre, "cell 0 3,alignx left");
		
		textFieldNombre = new JTextField();
		getContentPane().add(textFieldNombre, "cell 1 3,growx");
		textFieldNombre.setColumns(10);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		getContentPane().add(lblFechaNacimiento, "cell 3 3,alignx trailing");
		
		textFieldFecha = new WebDateField();
		getContentPane().add(textFieldFecha, "cell 4 3,growx");
		
		
		JLabel lblApellidos = new JLabel("Apellidos");
		getContentPane().add(lblApellidos, "cell 0 5,alignx left");
		
		textFieldApellidos = new JTextField();
		getContentPane().add(textFieldApellidos, "cell 1 5,growx");
		textFieldApellidos.setColumns(10);
		
		lblCargo = new JLabel("Cargo");
		getContentPane().add(lblCargo, "cell 3 5,alignx left");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrativo", "Mécanico", "Gerente", "Comercial"}));
		getContentPane().add(comboBox, "cell 4 5,growx");
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		getContentPane().add(lblDomicilio, "cell 0 7,alignx left");
		
		textFieldDomicilio = new JTextField();
		getContentPane().add(textFieldDomicilio, "cell 1 7,growx");
		textFieldDomicilio.setColumns(10);
		
		lblPassword = new JLabel("Password");
		getContentPane().add(lblPassword, "cell 3 7,alignx left");
		
		passwordField = new JPasswordField();
		getContentPane().add(passwordField, "cell 4 7,growx");
		
		btnAddUser = new JButton("ADD USER");
		getContentPane().add(btnAddUser, "cell 0 8");
		
		btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel, "cell 1 8");

	}

}
