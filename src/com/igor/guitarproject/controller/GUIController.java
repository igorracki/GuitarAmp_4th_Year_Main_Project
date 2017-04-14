package com.igor.guitarproject.controller;

import com.igor.guitarproject.gui.MainGUI;

/**
 * Class controlling the GUI interface.
 * @author Igor
 *
 */
public class GUIController {

	private MainController main_c;
	private MainGUI frame;
	
	/**
	 * Initialize the GUI.
	 * @param main_c
	 */
	public GUIController(MainController main_c) {
		this.main_c = main_c;
		
		frame = new MainGUI(this);
		frame.setVisible(true);
	}
	
	/**
	 * On Button listener.
	 */
	public void buttonOn() {
		main_c.getAudioController().startStream();
	}
	
	/**
	 * Off Button listener.
	 */
	public void buttonOff() {
		System.exit(0);
	}
	
	/**
	 * Filter On Button listener.
	 * @param double cutoff
	 * @param double frequency
	 */
	public void startFilter(double cutoff, double frequency) {
		main_c.getAudioController().getEffectsController().setLowPass(cutoff);
		main_c.getAudioController().getEffectsController().setFrequency(frequency);
		main_c.getAudioController().getEffectsController().setCurrentFilter("lowpass");
	}
	
	/**
	 * Filter Off Button listener.
	 */
	public void stopFilter() {
		main_c.getAudioController().getEffectsController().setCurrentFilter("none");
	}
	
	/**
	 * Filter Sliders listener.
	 * @param double cutoff
	 * @param double frequency
	 */
	public void updateFilter(double cutoff, double frequency) {
		if(main_c.getAudioController().getEffectsController().isFilterActive()) {
			main_c.getAudioController().getEffectsController().setLowPass(cutoff);
			main_c.getAudioController().getEffectsController().setFrequency(frequency);
			main_c.getAudioController().getEffectsController().updateFilter();
		}
	}
	
	/**
	 * Overdrive Button listener.
	 * @param drive
	 */
	public void startOverdrive(double drive) {
		main_c.getAudioController().getEffectsController().setDrive(drive);
		main_c.getAudioController().getEffectsController().setCurrentEffect("overdrive");
	}
	
	/**
	 * Drive Slider listener.
	 * @param double drive
	 */
	public void updateDrive(double drive) {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setDrive(drive);
			main_c.getAudioController().getEffectsController().updateOverdrive();
		}
	}
	
	/**
	 * Delay Button listener.
	 * @param int delay_length
	 * @param double delay_feedback
	 */
	public void startDelay(int delay_length, double delay_feedback) {
		main_c.getAudioController().getEffectsController().setDelayLength(delay_length);
		main_c.getAudioController().getEffectsController().setDelayFeedback(delay_feedback);
		main_c.getAudioController().getEffectsController().setCurrentEffect("delay");
	}
	
	/**
	 * Delay Sliders listener.
	 * @param int delay_length
	 * @param double delay_feedback
	 */
	public void updateDelay(int delay_length, double delay_feedback) {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setDelayLength(delay_length);
			main_c.getAudioController().getEffectsController().setDelayFeedback(delay_feedback);
			main_c.getAudioController().getEffectsController().updateDelay();
		}
	}
	
	/**
	 * Stop Effect listener.
	 */
	public void stopEffect() {
		if(main_c.getAudioController().getEffectsController().isEffectActive()) {
			main_c.getAudioController().getEffectsController().setCurrentEffect("none");
		}
	}
	
	/*********************** 	GETTERS & SETTERS 	***********************/
	public MainController getMain_c() {
		return main_c;
	}
}
