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
	
	/**
	 * Constructor del controlador
	 * @param vista la vista que va a controlar el controlador
	 * @param modelo modelo al que va a acceder el controlador
	 */
	public ControladorPrincipal(VistaPrincipal vista, MyOracleDataBase modelo) {
		
		
		this.vista = vista;
		this.modelo = modelo;
		
		inicializar();
		
	}
	
	/**
	 * 
	 * Método que inicializa los botones 
	 * 
	 */
	private void inicializar() {
		
		loginView = new LoginView();
		
		desktopPane = vista.getDesktopPane();
		properties = new PropertiesView();
		
		
		
		
		
		
		
		
		
		
	
		
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

	/**
	 * 
	 * Recoge los eventos al presionar los botones
	 *
	 */
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

	/**
	 * 
	 * Inicializa el Frame interno de la factura y los asigna a su controlador junto al modelo de datos
	 * 
	 */
	private void openInvoice() {
		in = new JIFInvoice();
		inc = new InvoiceController(modelo, in);
		inc.go();
		
	}

	/**
	 * 
	 * Inicializa el Frame interno de los vehículos y los asigna a su contralador junto al modelo de datos
	 * 
	 */
	private void openVehicle() {
		vv = new VehicleView();
		vehicleController = new VehicleTableController(modelo,vv);
		vehicleController.go();
		
	}

	/**
	 * 
	 * Inicializa el Frame interno de los clientes y los asigna a su contralador junto al modelo de datos
	 * 
	 */
	private void openCustomer() {
		
		ctv = new ClientTableView();
		ctc = new CustomerTableController(modelo,ctv);
		ctc.go();
	}

	/**
	 * 
	 * Inicializa el Frame interno de los empleados y lo asigna a su controlador junto al modelo de datos 
	 * 
	 */
	private void openETable() {
		employeeView = new EmployeeTableView();
		employeeController = new EmployeeTableController(employeeView, modelo);
		employeeController.go();
		
		
	}

	/**
	 * 
	 * Método que sirve para guardar las nuevas propiedades que ha insertado el usuario a través de la ventana de propiedades
	 * 
	 */
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
	/**
	 * 
	 * Método que dado un contenedro comprueba si hay algún campo vacío
	 * @param cont el contenedor del cual se va a comprobar si esta vacío
	 * @return devuelve un booleano según si en el panel hay un campo de texto vacío
	 */
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

	/**
	 * 
	 * Método que se encarga de abrir la ventana de propiedades
	 * 
	 */
	private void openPreferences() {
		
		if(estaAbierto(properties) == false) {
			
			addJInternalFrame(properties);
			properties.getBtnSave().addActionListener(this);
		
			
			properties.getBtnSave().setActionCommand("Save properties");
		}
		
	}

	/**
	 * 
	 * Método que una vez dado al botón de cerrar sesión habilita el botón de iniciar sesión y deshabilita el resto de botones del menú
	 * 
	 */
	private void logout() {
		
		vista.getButtonLogin().setEnabled(true);
		switchButtons(false);
		
	}
	/**
	 * 
	 * Una vez introducido el usuario y la contrasea las comprueba y inicia la sesión si las dos comprobaciones son correctas
	 * 
	 */
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
	
	/**
	 * 
	 * Método que sirve para cambiar el estado de todos los botones del menú
	 * @param change Es el parámetro al que cambiará la acessibilidad de los botones
	 */
	private void switchButtons(boolean change) {
		
		vista.getButtonnLogout().setEnabled(change);
		vista.getButtonClient().setEnabled(change);
		vista.getButtonVehicle().setEnabled(change);
		vista.getBtnCustomer().setEnabled(change);
		vista.getBtnFactura().setEnabled(change);
		
	}

	
	/**
	 * 
	 * Método que sirve para abrir la ventana del login
	 * 
	 */
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
	
	/**
	 * 
	 * Método al que se le pide un JInternalFrame y lo anyade a la vista
	 * 
	 * @param jif el JInternalFrame que se anyadira a la vista
	 */
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
	
	/**
	 * Comprueba si el JInternalFrame esta abierto
	 * @param internal El JInternalFrame que se comprueba si esta abierto
	 * @return Devuelve un booleano según si esta abierto el JInternalFrame o no
	 */
	static boolean estaAbierto(JInternalFrame internal) {
		
		boolean encontrado = false;
		JInternalFrame[] frames = desktopPane.getAllFrames();
		
		for(JInternalFrame frame : frames) {
			if(frame == internal) 
				encontrado = true;
		}
			
		
		return encontrado;
	}
	
	/**
	 * 
	 * Método que sirve para centrar el JInternalFrame que anyade al desktop
	 * @param jif JInternalFrame que se quiere centrar
	 */
	static void centrar(JInternalFrame jif) {
		
		Dimension deskSize = desktopPane.getSize();
		Dimension ifSize = jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width)/ 2, (deskSize.height - ifSize.height)/2);
		
	}
	
	
	

	
}
