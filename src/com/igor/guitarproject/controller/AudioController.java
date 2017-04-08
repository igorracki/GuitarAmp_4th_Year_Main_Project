package com.igor.guitarproject.controller;

import com.igor.guitarproject.audio.MicrophoneInputEngine;
import com.igor.guitarproject.audio.SpeakerOutputEngine;

public class AudioController {

	private MainController main_c;
	
	// Audio Setup.
	private float sample_rate = 44100.0f;
	private int sample_size_in_bits = 16;
	private int channels = 1;
	private boolean sign_data = true;
	private boolean big_endian = false;
	private int buffer_size = 4096;
	private int read_length = 2;
	
	// Audio Engines.
	private MicrophoneInputEngine mic_input_engine;
	private SpeakerOutputEngine speaker_output_engine;
	private Thread mic_engine_thread;
	
	public AudioController(MainController main_c) {
		this.main_c = main_c;
		
		System.out.println("Opening Audio Interfaces.");
		mic_input_engine = new MicrophoneInputEngine(this);
		speaker_output_engine = new SpeakerOutputEngine(this);
	}
	
	public void startStream() {
		mic_input_engine.startLine();
		speaker_output_engine.startLine();
		
		mic_engine_thread = new Thread(mic_input_engine);
		mic_engine_thread.start();
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public MainController getMainController() {
		return main_c;
	}

	public float getSampleRate() {
		return sample_rate;
	}

	public int getSampleSizeInBits() {
		return sample_size_in_bits;
	}

	public int getChannels() {
		return channels;
	}

	public boolean isSignData() {
		return sign_data;
	}

	public boolean isBigEndian() {
		return big_endian;
	}

	public int getBufferSize() {
		return buffer_size;
	}

	public int getReadLength() {
		return read_length;
	}

	public SpeakerOutputEngine getSpeakerOutputEngine() {
		return speaker_output_engine;
	}
}