package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.extended.date.WebDateField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;

public class AddModifyCustomer extends JInternalFrame {
	private JTextField txtDni;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtAddress;
	private JTextField txtEmail;
	private WebDateField txtBirthday;
	private JTextField txtCp;
	private JLabel labimg;
	private JComboBox<String> comboBox;
	private JButton btnAdd;
	private JButton btnCancel;
	private byte[] image;



	/**
	 * Create the frame.
	 */
	public AddModifyCustomer(String buttonText) {
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
		
		btnAdd = new JButton(buttonText);
		panel_2.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		panel_1.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][]"));
		
		JLabel lblDni = new JLabel("DNI");
		panel_1.add(lblDni, "cell 0 0,alignx left");
		
		txtDni = new JTextField();
		panel_1.add(txtDni, "cell 1 0,growx");
		txtDni.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panel_1.add(lblEmail, "cell 2 0,alignx left");
		
		txtEmail = new JTextField();
		panel_1.add(txtEmail, "cell 3 0,growx");
		txtEmail.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		panel_1.add(lblName, "cell 0 1,alignx left");
		
		txtName = new JTextField();
		panel_1.add(txtName, "cell 1 1,growx");
		txtName.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panel_1.add(lblBirthday, "cell 2 1,alignx left");
		
		txtBirthday = new WebDateField();
		panel_1.add(txtBirthday, "cell 3 1,growx");
		
		
		JLabel lblSurname = new JLabel("Surname");
		panel_1.add(lblSurname, "cell 0 2,alignx left");
		
		txtSurname = new JTextField();
		panel_1.add(txtSurname, "cell 1 2,growx");
		txtSurname.setColumns(10);
		
		JLabel lblDrivingLicense = new JLabel("Driving License");
		panel_1.add(lblDrivingLicense, "cell 2 2,alignx trailing");
		
		comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "Z"}));
		panel_1.add(comboBox, "cell 3 2,growx");
		
		JLabel lblAddress = new JLabel("Address");
		panel_1.add(lblAddress, "cell 0 3,alignx left");
		
		txtAddress = new JTextField();
		panel_1.add(txtAddress, "cell 1 3,growx");
		txtAddress.setColumns(10);
		
		JLabel lblCp = new JLabel("CP");
		panel_1.add(lblCp, "cell 2 3,alignx left");
		
		txtCp = new JTextField();
		panel_1.add(txtCp, "cell 3 3,growx");
		txtCp.setColumns(10);
		
		labimg = new JLabel("");
		labimg.setIcon(new ImageIcon(AddModifyCustomer.class.getResource("/com/mordor/mordorLloguer/assets/nophotoe.jpg")));
		
		JLabel lblDaleClickA = new JLabel("Dale click a la imagen para cambiarla");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(200)
					.addComponent(labimg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
					.addComponent(labimg)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDaleClickA)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
	
	
	public void setImage(byte[] image) {
		
		this.image = image;
		
		if(image != null) {
			
			try {
				BufferedImage ima = null;
				InputStream in = new ByteArrayInputStream(image);
				
				ima = ImageIO.read(in);
				
				ImageIcon icono = new ImageIcon(ima);
				Image imageToResize = icono.getImage();
				Image nuevaResized = imageToResize.getScaledInstance(100, 133, java.awt.Image.SCALE_SMOOTH);
				
				labimg.setIcon(new ImageIcon(nuevaResized));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			labimg.setIcon(new ImageIcon(AddModifyCustomer.class.getResource("/com/mordor/mordorLloguer/assets/nophotoe.jpg")));
		}
		
	}


	public JTextField getTxtDni() {
		return txtDni;
	}



	public JTextField getTxtName() {
		return txtName;
	}



	public JTextField getTxtSurname() {
		return txtSurname;
	}



	public JTextField getTxtAddress() {
		return txtAddress;
	}



	public JTextField getTxtEmail() {
		return txtEmail;
	}



	public WebDateField getTxtBirthday() {
		return txtBirthday;
	}



	public JTextField getTxtCp() {
		return txtCp;
	}



	public JLabel getLabelImg() {
		return labimg;
	}



	public JComboBox<String> getComboBox() {
		return comboBox;
	}



	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnCancel() {
		return btnCancel;
	}



	public byte[] getImage() {
		return image;
	}
	
	
}
