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
import com.mordor.mordorLloguer.view.ClientTableView;
import com.mordor.mordorLloguer.view.EmployeeTableView;
import com.mordor.mordorLloguer.view.JIFInvoice;
import com.mordor.mordorLloguer.view.LoginView;
import com.mordor.mordorLloguer.view.PropertiesView;
import com.mordor.mordorLloguer.view.VehicleView;
import com.mordor.mordorLloguer.view.VistaPrincipal;

public class ControladorPrincipal implements ActionListener {

	private VistaPrincipal vista;
	private MyOracleDataBase modelo;
	private LoginView loginView;
	private EmployeeTableController employeeController;
	private VehicleTableController vehicleController;
	private InvoiceController inc;
	private EmployeeTableView employeeView;
	private static JDesktopPane desktopPane;
	private PropertiesView properties;
	private CustomerTableController ctc;
	private ClientTableView ctv;
	private VehicleView vv;
	private JIFInvoice in;
	
	
	public ControladorPrincipal(VistaPrincipal vista, MyOracleDataBase modelo) {
		
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
		loginView = new LoginView();
		
		desktopPane = vista.getDesktopPane();
		properties = new PropertiesView();
		employeeView = new EmployeeTableView();
		ctv = new ClientTableView();
		vv = new VehicleView();
		in = new JIFInvoice();
		
		
		employeeController = new EmployeeTableController(employeeView, modelo);
		ctc = new CustomerTableController(modelo,ctv);
		vehicleController = new VehicleTableController(modelo,vv);
		inc = new InvoiceController(modelo, in);
	
		
		//Action Listener
		vista.getButtonLogin().addActionListener(this);
		vista.getButtonnLogout().addActionListener(this);
		vista.getMntmPreferences().addActionListener(this);
		vista.getButtonClient().addActionListener(this);
		vista.getBtnCustomer().addActionListener(this);
		vista.getButtonVehicle().addActionListener(this);
		vista.getBtnFactura().addActionListener(this);
		
		
		
		
		//Action Command
		vista.getButtonLogin().setActionCommand("Openlogin");
		vista.getButtonnLogout().setActionCommand("Logout");
		vista.getMntmPreferences().setActionCommand("Open preferences");
		vista.getButtonClient().setActionCommand("Open ETable");
		vista.getBtnCustomer().setActionCommand("Open CUstomer");
		vista.getButtonVehicle().setActionCommand("Open VTable");
		vista.getBtnFactura().setActionCommand("Open Invoice");
		
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
		} else if(comand.equals("Open ETable")) {
			openETable();
		} else if(comand.equals("Open CUstomer")) {
			openCustomer();
		} else if(comand.equals("Open VTable")) {
			openVehicle();
		} else if(comand.equals("Open Invoice")) {
			openInvoice();
		}
		 
	}

	private void openInvoice() {
		
		inc.go();
		
	}

	private void openVehicle() {
		
		
		vehicleController.go();
		
	}

	private void openCustomer() {
	
		ctc.go();
	}

	private void openETable() {
		
		employeeController.go();
		
		
	}

	private void saveProperties() {
		
		if(comprobarTexto(properties.getContentPane()) == true) {
			JOptionPane.showMessageDialog(vista, "No has rellenado todos los campos", "Erros", JOptionPane.ERROR_MESSAGE);
		} else {
			Config.getInstance().setDriver(properties.getTxtDriver().getText());
			Config.getInstance().setUrl(properties.getTxtUrl().getText());
			Config.getInstance().setUser(properties.getTxtUser().getText());
			Config.getInstance().setPassword(String.valueOf(properties.getTxtPass().getPassword()));
		}
		
	}

	static boolean comprobarTexto(Container cont) {
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
		switchButtons(false);
		
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
						switchButtons(true);
						loginView.dispose();
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
	
	private void switchButtons(boolean change) {
		
		vista.getButtonnLogout().setEnabled(change);
		vista.getButtonClient().setEnabled(change);
		vista.getButtonVehicle().setEnabled(change);
		vista.getBtnCustomer().setEnabled(change);
		vista.getBtnFactura().setEnabled(change);
		
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
