package com.mordor.mordorLloguer;

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;
import com.mordor.mordorLloguer.view.VistaPrincipal;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
