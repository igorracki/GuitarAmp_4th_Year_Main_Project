package com.igor.guitarproject.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.igor.guitarproject.controller.AudioController;

public class MicrophoneInputEngine implements Runnable {
	
	private AudioController audio_c;
	private TargetDataLine microphone_line;
	private AudioFormat microphone_format;
	
	private int amount_read;
	private int read_length;
	private byte[] data;
	
	public MicrophoneInputEngine(AudioController audio_c) {
		this.audio_c = audio_c;
		
		try {
			microphone_format = new AudioFormat(audio_c.getSampleRate(), audio_c.getSampleSizeInBits(), audio_c.getChannels(), audio_c.isSignData(), audio_c.isBigEndian());
			microphone_line = AudioSystem.getTargetDataLine(microphone_format);
			microphone_line.open(microphone_format, audio_c.getBufferSize());
			System.out.println("Microphone Line Opened.");
			
			read_length = audio_c.getReadLength();
			data = new byte[read_length];
		} catch (LineUnavailableException e) {
			System.out.println("Microphone Line Unavailable!");
		}
	}
	
	@Override
	public void run() {
		System.out.println("Stream Started.");
		
		while(true) {
			amount_read = microphone_line.read(data, 0, read_length);
			
			short audio = getAudioSample(data);
			audio_c.getSpeakerOutputEngine().playSample(audio);
		}
	}
	
	private short getAudioSample(byte[] data) {
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.put(data[0]);
		bb.put(data[1]);
		return bb.getShort(0);
	}
	
	public void startLine() {
		microphone_line.start();
		System.out.println("Microphone Started.");
	}
	
	public void stopLine() {
		microphone_line.close();
		System.out.println("Microphone Closed.");
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public TargetDataLine getMicrophoneLine() {
		return microphone_line;
	}

	public void setMicrophoneLine(TargetDataLine microphone_line) {
		this.microphone_line = microphone_line;
	}
}
