package com.igor.guitarproject.controller;

import com.igor.guitarproject.main.TempUserInput;

public class MainController {

	private AudioController audio_c;
	private TempUserInput user_input;
	Thread user_input_thread;
	
	public MainController() {
		audio_c = new AudioController(this);
		audio_c.startStream();
		
		user_input = new TempUserInput(audio_c);
		user_input_thread = new Thread(user_input);
		user_input_thread.start();
	}
	
	/*********************** 	GETTERS & SETTERS 	***********************/
	public AudioController getAudioController() {
		return audio_c;
	}

	public void setAudioController(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
}
