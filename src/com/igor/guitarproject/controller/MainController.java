package com.igor.guitarproject.controller;

/**
 * Class controlling other controllers.
 * Executed by the main thread.
 * @author Igor
 *
 */
public class MainController {

	private AudioController audio_c;
	private GUIController gui_c;
	
	/**
	 * Initialize controllers.
	 */
	public MainController() {
		gui_c = new GUIController(this);
		audio_c = new AudioController(this);
	}
	
	
	/*********************** 	GETTERS & SETTERS 	***********************/
	public AudioController getAudioController() {
		return audio_c;
	}

	public void setAudioController(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
	public GUIController getGuiC() {
		return gui_c;
	}
}
