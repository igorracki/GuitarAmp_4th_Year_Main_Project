package com.igor.guitarproject.controller;

public class MainController {

	private AudioController audio_c;
	
	public MainController() {
		audio_c = new AudioController(this);
		audio_c.startStream();
	}
	
	/*********************** 	GETTERS & SETTERS 	***********************/
	public AudioController getAudioController() {
		return audio_c;
	}

	public void setAudioController(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
}
