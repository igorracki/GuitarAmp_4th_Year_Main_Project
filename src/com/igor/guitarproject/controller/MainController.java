package com.igor.guitarproject.controller;

public class MainController {

	private AudioController audio_c;
	private GUIController gui_c;
	
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
	
}
