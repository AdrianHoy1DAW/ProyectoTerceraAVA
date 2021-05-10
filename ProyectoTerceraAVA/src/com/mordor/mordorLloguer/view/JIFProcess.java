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

public class JIFProcess extends JInternalFrame {
	private JProgressBar progressBar;



	/**
	 * Create the frame.
	 */
	public JIFProcess() {
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		
		JButton btnCancel = new JButton("Cancel");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(174)
							.addComponent(btnCancel)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(37))
		);
		panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][grow,fill]"));
		
		progressBar = new JProgressBar();
		panel.add(progressBar, "cell 0 0,growx");
		getContentPane().setLayout(groupLayout);

	}



	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	
}
