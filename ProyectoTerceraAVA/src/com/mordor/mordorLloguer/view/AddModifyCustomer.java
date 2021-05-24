package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class AddModifyCustomer extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel label;
	private JComboBox comboBox;
	private JButton btnAdd;
	private JButton btnCancel;



	/**
	 * Create the frame.
	 */
	public AddModifyCustomer() {
		setClosable(true);
		setBounds(100, 100, 541, 560);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAdd = new JButton("Add");
		panel_2.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		panel_1.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][]"));
		
		JLabel lblDni = new JLabel("DNI");
		panel_1.add(lblDni, "cell 0 0,alignx left");
		
		textField = new JTextField();
		panel_1.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panel_1.add(lblEmail, "cell 2 0,alignx left");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "cell 3 0,growx");
		textField_4.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName, "cell 0 1,alignx left");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panel_1.add(lblBirthday, "cell 2 1,alignx left");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "cell 3 1,growx");
		textField_5.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		panel_1.add(lblSurname, "cell 0 2,alignx left");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "cell 1 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving License");
		panel_1.add(lblDrivingLicense, "cell 2 2,alignx trailing");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "Z"}));
		panel_1.add(comboBox, "cell 3 2,growx");
		
		JLabel lblAddress = new JLabel("Address");
		panel_1.add(lblAddress, "cell 0 3,alignx left");
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);
		
		JLabel lblCp = new JLabel("CP");
		panel_1.add(lblCp, "cell 2 3,alignx left");
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "cell 3 3,growx");
		textField_6.setColumns(10);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AddModifyCustomer.class.getResource("/com/mordor/mordorLloguer/assets/nophotoe.jpg")));
		
		JLabel lblDaleClickA = new JLabel("Dale click a la imagen para cambiarla");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(200)
					.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(207))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(139)
					.addComponent(lblDaleClickA, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(106))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDaleClickA)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
