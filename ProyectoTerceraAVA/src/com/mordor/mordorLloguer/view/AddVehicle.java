package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddVehicle extends JInternalFrame {
	private JTextField txtMatricula;
	private JTextField txtPrecio;
	private JTextField txtMarca;
	private JTextField txtDescripcion;
	private JTextField txtColor;
	private JTextField txtCilindrada;
	private WebDateField txtFecha;
	private JTextField txtArriba;
	private JTextField txtAbajo;
	private JButton btnAdd;
	private JButton btnCancel;
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxCarnet;
	private DefaultComboBoxModel<String> dcm;
	private DefaultComboBoxModel<String> dm;
	private JComboBox comboBoxMotor;


	/**
	 * Create the frame.
	 */
	public AddVehicle(String titulo, String campo, String camp, int tipo) {
		setClosable(true);
		setBounds(100, 100, 403, 584);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAdd = new JButton("Add");
		panel_2.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		
		JLabel lblMatricula = new JLabel("Matricula");
		panel_1.add(lblMatricula, "cell 0 0,alignx left");
		
		txtMatricula = new JTextField();
		panel_1.add(txtMatricula, "cell 1 0,growx");
		txtMatricula.setColumns(10);
		
		JLabel lblPreciodia = new JLabel("Preciodia");
		panel_1.add(lblPreciodia, "cell 0 1,alignx left");
		
		txtPrecio = new JTextField();
		panel_1.add(txtPrecio, "cell 1 1,growx");
		txtPrecio.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		panel_1.add(lblMarca, "cell 0 2,alignx left");
		
		txtMarca = new JTextField();
		panel_1.add(txtMarca, "cell 1 2,growx");
		txtMarca.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		panel_1.add(lblDescripcion, "cell 0 3,alignx left");
		
		txtDescripcion = new JTextField();
		panel_1.add(txtDescripcion, "cell 1 3,growx");
		txtDescripcion.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		panel_1.add(lblColor, "cell 0 4,alignx left");
		
		txtColor = new JTextField();
		panel_1.add(txtColor, "cell 1 4,growx");
		txtColor.setColumns(10);
		
		JLabel lblMotor = new JLabel("Motor");
		panel_1.add(lblMotor, "cell 0 5,alignx left");
		
		comboBoxMotor = new JComboBox();
		panel_1.add(comboBoxMotor, "cell 1 5,growx");
		
		JLabel lblCilindrada = new JLabel("Cilindrada");
		panel_1.add(lblCilindrada, "cell 0 6,alignx left");
		
		txtCilindrada = new JTextField();
		panel_1.add(txtCilindrada, "cell 1 6,growx");
		txtCilindrada.setColumns(10);
		
		JLabel lblFechaAdq = new JLabel("Fecha Adq.");
		panel_1.add(lblFechaAdq, "cell 0 7,alignx left");
		
		txtFecha = new WebDateField();
		panel_1.add(txtFecha, "cell 1 7,growx");
		
		
		JLabel lblEstado = new JLabel("Estado");
		panel_1.add(lblEstado, "cell 0 9,alignx left");
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] {"preparado", "alquilado", "taller", "reservado", "baja"}));
		panel_1.add(comboBoxEstado, "cell 1 9,growx");
		
		JLabel lblCarnet = new JLabel("Carnet");
		panel_1.add(lblCarnet, "cell 0 10,alignx left");
		
		comboBoxCarnet = new JComboBox();
		panel_1.add(comboBoxCarnet, "cell 1 10,growx");
		
		JLabel lblCampo1 = new JLabel("");	
		panel_1.add(lblCampo1, "cell 0 11,alignx left");
		
		
		txtArriba = new JTextField();
		panel_1.add(txtArriba, "cell 1 11,growx");
		txtArriba.setColumns(10);
		
		JLabel lblCampo2 = new JLabel("");
		panel_1.add(lblCampo2, "cell 0 12,alignx left");
		
		txtAbajo = new JTextField();
		panel_1.add(txtAbajo, "cell 1 12,growx");
		txtAbajo.setColumns(10);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel(titulo);
		panel.add(label);
		getContentPane().setLayout(groupLayout);
		
		if(campo == null) {
			txtArriba.setVisible(false);
		} else {
			lblCampo1.setText(campo);
		}
		
		if(camp == null) {
			txtAbajo.setVisible(false);
		} else {
			lblCampo2.setText(camp);
		}
		
		dcm = new DefaultComboBoxModel<>();
		dm = new DefaultComboBoxModel<>();
		
		
		if(tipo == VehicleView.CAR) {
			ArrayList<String> vetor = new ArrayList<>();
			ArrayList<String> v = new ArrayList<>();
			
			
			vetor.add("B");
			
			v.add("hibrido enchufable");
			v.add("electrico");
			v.add("gasolina");
			
			dcm.addAll(vetor);
			dm.addAll(v);
			
			comboBoxCarnet.setModel(dcm);
			comboBoxCarnet.setSelectedIndex(0);
			comboBoxMotor.setModel(dm);
			comboBoxMotor.setSelectedIndex(0);
			
 		} else if(tipo == VehicleView.MINIBUS) {
 			
			ArrayList<String> vetor = new ArrayList<>();
			ArrayList<String> v = new ArrayList<>();
			
			
			vetor.add("B");
			vetor.add("D");
			
			v.add("hibrido enchufable");
			v.add("electrico");
			v.add("gasolina");
			v.add("diesel");
			
			dcm.addAll(vetor);
			dm.addAll(v);
			
			comboBoxCarnet.setModel(dcm);
			comboBoxCarnet.setSelectedIndex(0);
			comboBoxMotor.setModel(dm);
			comboBoxMotor.setSelectedIndex(0);
 			
 		} else if(tipo == VehicleView.TRUCK) {
 			
			ArrayList<String> vetor = new ArrayList<>();
			ArrayList<String> v = new ArrayList<>();
			
			
			vetor.add("E");
			
			
			v.add("diesel");
			v.add("gasolina");
			
			dcm.addAll(vetor);
			dm.addAll(v);
			
			comboBoxCarnet.setModel(dcm);
			comboBoxCarnet.setSelectedIndex(0);
			comboBoxMotor.setModel(dm);
			comboBoxMotor.setSelectedIndex(0);
 			
 		} else if(tipo == VehicleView.VAN) {
			ArrayList<String> vetor = new ArrayList<>();
			ArrayList<String> v = new ArrayList<>();
			
			
			vetor.add("C");
			
			
			v.add("electrico");
			v.add("gasolina");
			
			dcm.addAll(vetor);
			dm.addAll(v);
			
			comboBoxCarnet.setModel(dcm);
			comboBoxCarnet.setSelectedIndex(0);
			comboBoxMotor.setModel(dm);
			comboBoxMotor.setSelectedIndex(0);
 		}

	}


	public JTextField getTxtMatricula() {
		return txtMatricula;
	}


	public JTextField getTxtPrecio() {
		return txtPrecio;
	}


	public JTextField getTxtMarca() {
		return txtMarca;
	}


	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}


	public JTextField getTxtColor() {
		return txtColor;
	}





	public JTextField getTxtCilindrada() {
		return txtCilindrada;
	}


	public WebDateField getTxtFecha() {
		return txtFecha;
	}





	public JTextField getTxtArriba() {
		return txtArriba;
	}


	public JTextField getTxtAbajo() {
		return txtAbajo;
	}


	public JButton getBtnAdd() {
		return btnAdd;
	}


	public JButton getBtnCancel() {
		return btnCancel;
	}


	public JComboBox getComboBoxEstado() {
		return comboBoxEstado;
	}


	public JComboBox getComboBoxCarnet() {
		return comboBoxCarnet;
	}


	public JComboBox getComboBoxMotor() {
		return comboBoxMotor;
	}
	
	
}
