package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class LoginView extends JInternalFrame {
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JProgressBar progressBar;


	/**
	 * Create the frame.
	 */
	public LoginView() {
		setClosable(true);
		
		setBounds(100, 100, 465, 252);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][bottom]"));
		
		JLabel lblMemberLogin = new JLabel("Member Login");
		lblMemberLogin.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(lblMemberLogin, "cell 0 1,alignx center,aligny center");
		
		txtUsuario = new JTextField();
		getContentPane().add(txtUsuario, "cell 0 3,growx");
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		getContentPane().add(txtPassword, "cell 0 4,growx");
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, "cell 0 8,growx");
		
		btnLogin = new JButton("Login");
		getContentPane().add(btnLogin, "cell 0 10,growx,aligny bottom");

	}


	public JButton getBtnLogin() {
		return btnLogin;
	}


	public JTextField getTxtUsuario() {
		return txtUsuario;
	}


	public JPasswordField getTxtPassword() {
		return txtPassword;
	}
	
	

}
