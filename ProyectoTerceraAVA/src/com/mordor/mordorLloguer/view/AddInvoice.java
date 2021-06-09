package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddInvoice extends JInternalFrame {
	private JTextField txtDni;
	private JTextField txtMatricula;
	private WebDateField txtInicio;
	private WebDateField txtFinal;
	private JButton btnInsertar;
	private JButton btnCancelat;



	/**
	 * Create the frame.
	 */
	public AddInvoice() {
		setClosable(true);
		setBounds(100, 100, 385, 416);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnInsertar = new JButton("Insertar");
		panel_2.add(btnInsertar);
		
		btnCancelat = new JButton("Cancelar");
		panel_2.add(btnCancelat);
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		JLabel lblDni = new JLabel("DNI del Cliente");
		panel_1.add(lblDni, "cell 0 0,alignx trailing");
		
		txtDni = new JTextField();
		panel_1.add(txtDni, "cell 1 0,growx");
		txtDni.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula");
		panel_1.add(lblMatricula, "cell 0 1,alignx left");
		
		txtMatricula = new JTextField();
		panel_1.add(txtMatricula, "cell 1 1,growx");
		txtMatricula.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		panel_1.add(lblFechaInicio, "cell 0 2,alignx left");
		
		txtInicio = new WebDateField();
		panel_1.add(txtInicio, "cell 1 2,growx");
		
		
		JLabel lblFechaFinal = new JLabel("Fecha Final");
		panel_1.add(lblFechaFinal, "cell 0 3,alignx left");
		
		txtFinal = new WebDateField();
		panel_1.add(txtFinal, "cell 1 3,growx");
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblInsertandoFactura = new JLabel("INSERTANDO FACTURA");
		panel.add(lblInsertandoFactura);
		getContentPane().setLayout(groupLayout);

	}



	public JTextField getTxtDni() {
		return txtDni;
	}



	public JTextField getTxtMatricula() {
		return txtMatricula;
	}



	public WebDateField getTxtInicio() {
		return txtInicio;
	}



	public WebDateField getTxtFinal() {
		return txtFinal;
	}



	public JButton getBtnInsertar() {
		return btnInsertar;
	}



	public JButton getBtnCancelat() {
		return btnCancelat;
	}
	
	
}
