package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.alee.laf.table.WebTable;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class EmployeeTableView extends JInternalFrame {
	private JComboBox<String> comboBoxDatos;
	private WebTable table;
	private JComboBox<String> comboBoxAsc;
	private JButton btnAdd;
	private JButton btnDelete;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAdd;
	private JMenuItem mntmDelete;



	/**
	 * Create the frame.
	 */
	public EmployeeTableView() {
		setFrameIcon(new ImageIcon(EmployeeTableView.class.getResource("/com/mordor/mordorLloguer/assets/badge.png")));
		setClosable(true);
		setBounds(100, 100, 710, 489);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		btnAdd = new JButton("ADD");
		
		btnDelete = new JButton("DELETE");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(361, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnDelete))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new WebTable();
		table.setRowHeight(30);
		table.setAutoResizeMode ( JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setVisibleRowCount ( 5 );
        table.optimizeColumnWidths ( true );
        table.setOptimizeRowHeight ( true );
        table.setEditable ( true );
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mntmAdd = new JMenuItem("Add");
		popupMenu.add(mntmAdd);
		
		mntmDelete = new JMenuItem("Delete");
		popupMenu.add(mntmDelete);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por: ");
		panel.add(lblOrdenarPor);
		
		comboBoxDatos = new JComboBox<>();
		comboBoxDatos.setModel(new DefaultComboBoxModel<String>(new String[] {"DNI", "NOMBRE", "APELLIDOS", "DOMICILIO", "CP", "EMAIL", "FECHANAC", "CARGO"}));
		panel.add(comboBoxDatos);
		
		JLabel lblOrdenarPor_1 = new JLabel("Ordenar por: ");
		panel.add(lblOrdenarPor_1);
		
		comboBoxAsc = new JComboBox<>();
		comboBoxAsc.setModel(new DefaultComboBoxModel<String>(new String[] {"Ascendente", "Descendente"}));
		panel.add(comboBoxAsc);
		getContentPane().setLayout(groupLayout);

	}



	public JComboBox<String> getComboBoxDatos() {
		return comboBoxDatos;
	}



	public WebTable getTable() {
		return table;
	}



	public JComboBox<String> getComboBoxAsc() {
		return comboBoxAsc;
	}



	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	
	
	
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}



	public JMenuItem getMntmAdd() {
		return mntmAdd;
	}



	public JMenuItem getMntmDelete() {
		return mntmDelete;
	}



	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
