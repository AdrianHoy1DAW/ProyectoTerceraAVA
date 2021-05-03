package com.mordor.mordorLloguer.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.mordor.mordorLloguer.model.MyOracleDataBase;
import com.mordor.mordorLloguer.view.LoginView;
import com.mordor.mordorLloguer.view.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private MyOracleDataBase modelo;
	private LoginView loginView;
	
	
	public ControladorPrincipal(VistaPrincipal vista) {
		
		
		this.vista = vista;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
	    loginView = null;
		modelo = new MyOracleDataBase();
		
		//Action Listener
		vista.getButtonLogin().addActionListener(this);
		
		
		
		//Action Command
		vista.getButtonLogin().setActionCommand("Openlogin");
		
	}
	
	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String comand = arg0.getActionCommand();
		
		if(comand.equals("Openlogin")) {
			OpenLogin();
		} else if(comand.equals("login")) {
			login();
		}
		
	}

	private void login() {
		
		if(modelo.authenticate(loginView.getTxtUsuario().getText(), String.valueOf(loginView.getTxtPassword().getPassword())) == true) {
			JOptionPane.showMessageDialog(vista, "Login correcto","Información",JOptionPane.INFORMATION_MESSAGE);
			vista.getButtonLogin().setEnabled(false);
			vista.getButtonnLogout().setEnabled(true);
			
		} else {
			JOptionPane.showMessageDialog(vista, "Login incorrecto","Información",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

	private void OpenLogin() {
		
		
		if(estaAbierto(loginView) == false) {
			loginView = new LoginView();
			vista.getDesktopPane().add(loginView);
			loginView.setVisible(true);
			loginView.getBtnLogin().addActionListener(this);
			loginView.getBtnLogin().setActionCommand("login");
		} else {
			JOptionPane.showMessageDialog(vista, "Ya esta abierta la ventana de login","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private boolean estaAbierto(JInternalFrame internal) {
		
		boolean encontrado = false;
		for(Component c : vista.getDesktopPane().getComponents()) {
			
			if(c instanceof JInternalFrame) {
				if(((JInternalFrame)c).equals(internal)) {
					encontrado = true;
				}
			}
			
		}
		return encontrado;
	}

	
}
