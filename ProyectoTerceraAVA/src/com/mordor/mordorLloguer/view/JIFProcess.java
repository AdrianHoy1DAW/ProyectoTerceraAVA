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



	/**
	 * Create the frame.
	 */
	public JIFProcess(SwingWorker task,String msg) {
		setFrameIcon(new ImageIcon(JIFProcess.class.getResource("/com/mordor/mordorLloguer/assets/load.png")));
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				task.cancel(true);
				dispose();
			}
		});
		
		JLabel label = new JLabel(msg);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(166)
							.addComponent(label)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(189, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(170))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(label)
					.addGap(41)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(37))
		);
		panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][grow,fill]"));
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		panel.add(progressBar, "cell 0 0,growx");
		getContentPane().setLayout(groupLayout);

	}



	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
