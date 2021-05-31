package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JIFProcess extends JInternalFrame {
	private JProgressBar progressBar;
	private JLabel lblTexto;
	private JLabel label;



	/**
	 * Create the frame.
	 */
	public JIFProcess(SwingWorker task,String msg) {
		setFrameIcon(new ImageIcon(JIFProcess.class.getResource("/com/mordor/mordorLloguer/assets/load.png")));
		setBounds(100, 100, 453, 296);
		
		JPanel panel = new JPanel();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				task.cancel(true);
				dispose();
			}
		});
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCancel)
							.addGap(176))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCancel)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel_1.setLayout(new MigLayout("", "[grow,center]", "[][]"));
		
		label = new JLabel(msg);
		panel_1.add(label, "cell 0 0");
		
		lblTexto = new JLabel("");
		panel_1.add(lblTexto, "cell 0 1");
		panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][grow,fill]"));
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		panel.add(progressBar, "cell 0 0,growx");
		getContentPane().setLayout(groupLayout);

	}



	public JProgressBar getProgressBar() {
		return progressBar;
	}



	public JLabel getLblTexto() {
		return lblTexto;
	}



	public JLabel getLabel() {
		return label;
	}
}
