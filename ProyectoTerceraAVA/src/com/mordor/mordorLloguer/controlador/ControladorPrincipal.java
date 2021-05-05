package com.mordor.mordorLloguer.controlador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.mordor.mordorLloguer.config.Config;
import com.mordor.mordorLloguer.model.MyOracleDataBase;
import com.mordor.mordorLloguer.view.LoginView;
import com.mordor.mordorLloguer.view.PropertiesView;
import com.mordor.mordorLloguer.view.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private MyOracleDataBase modelo;
	private LoginView loginView;
	private static JDesktopPane desktopPane;
	private PropertiesView properties;
	
	
	public ControladorPrincipal(VistaPrincipal vista, MyOracleDataBase modelo) {
		
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
		loginView = new LoginView();
		
		desktopPane = vista.getDesktopPane();
		properties = new PropertiesView();
		
		//Action Listener
		vista.getButtonLogin().addActionListener(this);
		vista.getButtonnLogout().addActionListener(this);
		vista.getMntmPreferences().addActionListener(this);
		
		
		
		//Action Command
		vista.getButtonLogin().setActionCommand("Openlogin");
		vista.getButtonnLogout().setActionCommand("Logout");
		vista.getMntmPreferences().setActionCommand("Open preferences");
		
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
		} else if(comand.equals("Logout")) {
			logout();
		} else if(comand.equals("Open preferences")) {
			openPreferences();
		} else if(comand.equals("Save properties")) {
			saveProperties();
		}
		
	}

	private void saveProperties() {
		
		if(comprobarTexto(properties.getContentPane()) == true) {
			JOptionPane.showMessageDialog(vista, "No has rellenado todos los campos", "Erros", JOptionPane.ERROR_MESSAGE);
		} else {
			Config.getInstance().setProperties(properties.getTxtDriver().getText(), properties.getTxtUrl().getText(), properties.getTxtUser().getText(), String.valueOf(properties.getTxtPass().getPassword()));
		}
		
	}

	private boolean comprobarTexto(Container cont) {
		boolean empty = false;
		for(Component c : cont.getComponents()) {
			if(c instanceof JTextField) {
				if(((JTextField)c).getText().equals("")) {
					empty = true;
				}
			}
			if(c instanceof JPasswordField) {
				if((String.valueOf(((JPasswordField)c).getPassword()).equals(""))) {
					empty = true;
				}
			}
		}
		return empty;
	}

	private void openPreferences() {
		
		if(estaAbierto(properties) == false) {
			
			addJInternalFrame(properties);
			properties.getBtnSave().addActionListener(this);
		
			
			properties.getBtnSave().setActionCommand("Save properties");
		}
		
	}

	private void logout() {
		
		vista.getButtonLogin().setEnabled(true);
		vista.getButtonnLogout().setEnabled(false);
		
	}

	private void login() {
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				loginView.getProgressBar().setVisible(true);
				return modelo.authenticate(loginView.getTxtUsuario().getText(), String.valueOf(loginView.getTxtPassword().getPassword()));
			}
			
			@Override
			protected void done() {
				loginView.getProgressBar().setVisible(false);
				try {
					boolean validado = get();
					if(validado == true) {
						JOptionPane.showMessageDialog(vista, "Login correcto","Información",JOptionPane.INFORMATION_MESSAGE);
						vista.getButtonLogin().setEnabled(false);
						vista.getButtonnLogout().setEnabled(true);
						loginView.doDefaultCloseAction();
					} else {
						JOptionPane.showMessageDialog(vista, "Login incorrecto","Información",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} catch (ExecutionException e) {
					
					e.printStackTrace();
				}
				

				
			}
			
			
		};
		
		task.execute();
		
	}

	private void OpenLogin() {
		
		
		if(estaAbierto(loginView) == false) {
			addJInternalFrame(loginView);
			loginView.getBtnLogin().addActionListener(this);
			loginView.getBtnLogin().setActionCommand("login");
		} else {
			JOptionPane.showMessageDialog(vista, "Ya esta abierta la ventana de login","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		Action action = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		};
		
		loginView.getTxtUsuario().setAction(action);
		loginView.getTxtPassword().setAction(action);
		
	}
	
	static void addJInternalFrame(JInternalFrame jif) {
		
		desktopPane.add(jif);
		centrar(jif);
		jif.setVisible(true);
		try {
			jif.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}
	
	static boolean estaAbierto(JInternalFrame internal) {
		
		boolean encontrado = false;
		JInternalFrame[] frames = desktopPane.getAllFrames();
		
		for(JInternalFrame frame : frames) {
			if(frame == internal) 
				encontrado = true;
		}
			
		
		return encontrado;
	}
	
	static void centrar(JInternalFrame jif) {
		
		Dimension deskSize = desktopPane.getSize();
		Dimension ifSize = jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width)/ 2, (deskSize.height - ifSize.height)/2);
		
	}
	
	

	
}
