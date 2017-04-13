package com.igor.guitarproject.controller;

import java.awt.EventQueue;

import com.igor.guitarproject.gui.MainGUI;

public class GUIController {

	private MainController main_c;

	private GUIController this_c;
	
	private MainGUI frame;
	
	public GUIController(MainController main_c) {
		this.main_c = main_c;
		this.this_c = this;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainGUI(this_c);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void buttonOn() {
		main_c.getAudioController().startStream();
	}
	
	public void buttonOff() {
		System.exit(0);
	}
	
	public void startFilter(double cutoff, double frequency) {
		main_c.getAudioController().getEffectsController().setLowPass(cutoff);
		main_c.getAudioController().getEffectsController().setFrequency(frequency);
		main_c.getAudioController().getEffectsController().setCurrentFilter("lowpass");
	}
	
	public void stopFilter() {
		main_c.getAudioController().getEffectsController().setCurrentFilter("none");
	}
	
	public void updateFilter(double cutoff, double frequency) {
		if(main_c.getAudioController().getEffectsController().isFilterActive()) {
			main_c.getAudioController().getEffectsController().setLowPass(cutoff);
			main_c.getAudioController().getEffectsController().setFrequency(frequency);
			main_c.getAudioController().getEffectsController().updateFilter();
		}
	}
	
	public void startOverdrive(double drive) {
		main_c.getAudioController().getEffectsController().setDrive(drive);
		main_c.getAudioController().getEffectsController().setCurrentEffect("overdrive");
	}
	
	public void updateDrive(double drive) {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setDrive(drive);
			main_c.getAudioController().getEffectsController().updateOverdrive();
		}
	}
	
	public void startDelay(int delay_length, double delay_feedback) {
		main_c.getAudioController().getEffectsController().setDelayLength(delay_length);
		main_c.getAudioController().getEffectsController().setDelayFeedback(delay_feedback);
		main_c.getAudioController().getEffectsController().setCurrentEffect("delay");
	}
	
	public void updateDelay(int delay_length, double delay_feedback) {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setDelayLength(delay_length);
			main_c.getAudioController().getEffectsController().setDelayFeedback(delay_feedback);
			main_c.getAudioController().getEffectsController().updateDelay();
		}
	}
	
	public void stopEffect() {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setCurrentEffect("none");
		}
	}
	
	/*********************** 	GETTERS & SETTERS 	***********************/
	public MainController getMain_c() {
		return main_c;
	}

	public void setMain_c(MainController main_c) {
		this.main_c = main_c;
	}
}
