package com.igor.guitarproject.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.igor.guitarproject.controller.AudioController;

public class SpeakerOutputEngine {

	private AudioController audio_c;
	private SourceDataLine speaker_line;
	private AudioFormat speaker_format;
	
	public SpeakerOutputEngine(AudioController audio_c) {
		this.audio_c = audio_c;
		
		try {
			speaker_format = new AudioFormat(audio_c.getSampleRate(), audio_c.getSampleSizeInBits(), audio_c.getChannels(), audio_c.isSignData(), audio_c.isBigEndian());
			speaker_line = AudioSystem.getSourceDataLine(speaker_format);
			speaker_line.open(speaker_format, audio_c.getBufferSize());
			System.out.println("Speaker Line Opened.");
		} catch (LineUnavailableException e) {
			System.out.println("Speaker Line Unavailable!");
		}
	}
	
	public void playSample(short sample) {
		
		byte[] output_buffer = new byte[audio_c.getReadLength()];
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		bb.putShort(sample);
		output_buffer[0] = bb.get(0);
		output_buffer[1] = bb.get(1);
		
		speaker_line.write(output_buffer, 0, output_buffer.length);
	}
	
	public void startLine() {
		speaker_line.start();
		System.out.println("Speakers Started.");
	}
	
	public void stopLine() {
		speaker_line.drain();
		speaker_line.close();
		System.out.println("Speakers Closed.");
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public SourceDataLine getSpeakerLine() {
		return speaker_line;
	}

	public void setSpeakerLine(SourceDataLine speaker_line) {
		this.speaker_line = speaker_line;
	}
}
