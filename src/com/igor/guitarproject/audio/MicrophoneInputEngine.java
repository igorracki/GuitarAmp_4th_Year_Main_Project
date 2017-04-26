package com.igor.guitarproject.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.igor.guitarproject.controller.AudioController;

/**
 * Class handling the input from the microphone Line-In.
 * 
 * @author Igor
 *
 */
public class MicrophoneInputEngine implements Runnable {
	
	private AudioController audio_c;
	private TargetDataLine microphone_line;
	private AudioFormat microphone_format;
	
	private int amount_read;
	private int read_length;
	private byte[] data;
	
	/**
	 * Open the microphone line.
	 * 
	 * @param AudioController audio_c
	 */
	public MicrophoneInputEngine(AudioController audio_c) {
		this.audio_c = audio_c;
		
		try {
			microphone_format = new AudioFormat(audio_c.getSampleRate(), audio_c.getSampleSizeInBits(), audio_c.getChannels(), audio_c.isSignData(), audio_c.isBigEndian());
			microphone_line = AudioSystem.getTargetDataLine(microphone_format);
			microphone_line.open(microphone_format, audio_c.getBufferSize());
			System.out.println("Microphone Line Opened.");
			audio_c.getMainController().getGuiC().lineOpened();
			
			read_length = audio_c.getReadLength();
			data = new byte[read_length];
		} catch (LineUnavailableException e) {
			System.out.println("Microphone Line Unavailable!");
		}
	}
	
	/**
	 * I/O Thread for the sound engines.
	 */
	@Override
	public void run() {
		System.out.println("Stream Started.");
		
		while(true) {
			// Read bytes from the microphone buffer.
			amount_read = microphone_line.read(data, 0, read_length);
			
			// Convert bytes into Short audio samples.
			// One audio sample consists of two bytes.
			short[] audio = new short[amount_read/2];
			int i = 0;
			while(i < amount_read-2) {
				ByteBuffer bb = ByteBuffer.allocate(2);
            	 		bb.order(ByteOrder.LITTLE_ENDIAN);
            	 		bb.put(data[i]);
            	 		bb.put(data[i+1]);
            	 		audio[(i/2)] = bb.getShort(0);
            	 		i += 2;
			}
			
			// Pass the samples to the speaker engine.
			audio_c.getSpeakerOutputEngine().playSignal(audio);
		}
	}
	
	/**
	 * Start the microphone line.
	 */
	public void startLine() {
		microphone_line.start();
		System.out.println("Microphone Started.");
	}
	
	/**
	 * Close the microphone line.
	 */
	public void stopLine() {
		microphone_line.close();
		System.out.println("Microphone Closed.");
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public TargetDataLine getMicrophoneLine() {
		return microphone_line;
	}
}
