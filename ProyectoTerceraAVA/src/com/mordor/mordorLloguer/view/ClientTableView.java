package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

public class ClientTableView extends JInternalFrame {
	private JTextField textFieldDni;
	private JTextField textFieldName;
	private JTextField textFieldSur;
	private WebTable table;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnCancel;
	private JButton btnNewButton;
	private JComboBox comboBox;



	/**
	 * Create the frame.
	 */
	public ClientTableView() {
		setBounds(100, 100, 780, 476);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAdd = new JButton("Add");
		panel_1.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		btnEdit = new JButton("Edit");
		panel_1.add(btnEdit);
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		
		table = new WebTable();
		scrollPane.setViewportView(table);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDni = new JLabel("DNI");
		panel.add(lblDni);
		
		textFieldDni = new JTextField();
		panel.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panel.add(lblName);
		
		textFieldName = new JTextField();
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		panel.add(lblSurname);
		
		textFieldSur = new JTextField();
		panel.add(textFieldSur);
		textFieldSur.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("driving license");
		panel.add(lblDrivingLicense);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "A", "B", "C", "D", "E", "Z"}));
		panel.add(comboBox);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ClientTableView.class.getResource("/com/mordor/mordorLloguer/assets/print.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnNewButton);
		getContentPane().setLayout(groupLayout);

	}



	public JTextField getTextFieldDni() {
		return textFieldDni;
	}



	public JTextField getTextFieldName() {
		return textFieldName;
	}



	public JTextField getTextFieldSur() {
		return textFieldSur;
	}



	public WebTable getTable() {
		return table;
	}



	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnDelete() {
		return btnDelete;
	}



	public JButton getBtnEdit() {
		return btnEdit;
	}



	public JButton getBtnCancel() {
		return btnCancel;
	}



	public JButton getBtnNewButton() {
		return btnNewButton;
	}



	public JComboBox getComboBox() {
		return comboBox;
	}
	
	
}
