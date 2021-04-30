package com.mordor.mordorLloguer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class LoginView extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setBounds(100, 100, 450, 300);

	}

}
