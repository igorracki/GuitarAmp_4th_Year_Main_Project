package com.igor.guitarproject.controller;

import java.awt.EventQueue;

import com.igor.guitarproject.gui.MainGUI;

public class GUIController {

	private MainController main_c;
	
	private MainGUI frame;
	
	public GUIController(MainController main_c) {
		this.main_c = main_c;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
