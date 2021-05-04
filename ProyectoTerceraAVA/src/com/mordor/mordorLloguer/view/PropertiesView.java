package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PropertiesView extends JInternalFrame {
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JTextField txtUrl;
	private JTextField txtDriver;
	private JButton btnSave;


	/**
	 * Create the frame.
	 */
	public PropertiesView() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setFrameIcon(new ImageIcon(PropertiesView.class.getResource("/com/mordor/mordorLloguer/assets/config.png")));
		setBounds(100, 100, 565, 391);
		getContentPane().setLayout(new MigLayout("", "[121px][318px]", "[19px][19px][19px][19px][25px][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Driver");
		getContentPane().add(lblNewLabel, "cell 0 1,alignx left,aligny center");
		
		txtDriver = new JTextField();
		txtDriver.setColumns(10);
		getContentPane().add(txtDriver, "cell 1 1,growx,aligny top");
		
		JLabel lblModifyUrl_1 = new JLabel("Modify URL");
		getContentPane().add(lblModifyUrl_1, "cell 0 3,alignx left,aligny center");
		
		txtUrl = new JTextField();
		txtUrl.setColumns(10);
		getContentPane().add(txtUrl, "cell 1 3,growx,aligny top");
		
		JLabel lblModifyUrl = new JLabel("Modify User");
		getContentPane().add(lblModifyUrl, "cell 0 5,alignx left,aligny center");
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		getContentPane().add(txtUser, "cell 1 5,growx,aligny top");
		
		JLabel lblModifyPassword = new JLabel("Modify Password");
		getContentPane().add(lblModifyPassword, "cell 0 9,alignx left,aligny center");
		
		txtPass = new JPasswordField();
		getContentPane().add(txtPass, "cell 1 9,growx,aligny top");
		
		btnSave = new JButton("Save");
		getContentPane().add(btnSave, "cell 1 12,alignx center,aligny top");

	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public JPasswordField getTxtPass() {
		return txtPass;
	}

	public JTextField getTxtUrl() {
		return txtUrl;
	}

	public JTextField getTxtDriver() {
		return txtDriver;
	}

	public JButton getBtnSave() {
		return btnSave;
	}


	
}
