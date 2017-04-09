package com.igor.guitarproject.controller;

import com.igor.guitarproject.processor.Delay;
import com.igor.guitarproject.processor.Effect;
import com.igor.guitarproject.processor.Overdrive;

public class EffectsController {

	private AudioController audio_c;
	
	// Parameters for effects.
	private double drive;
	private int delay_length;
	private double delay_feedback;
	
	// Effects.
	private Effect current_effect;
	
	public EffectsController(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
	public boolean isEffectActive() {
		if(current_effect == null)
			return false;
		else
			return true;
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public Effect getCurrentEffect() {
		return current_effect;
	}
	
	public void setCurrentEffect(String effect) {
		switch(effect) {
			case "overdrive": current_effect = new Overdrive(drive); break;
			case "delay": current_effect = new Delay(delay_length, delay_feedback); break;
			case "none": current_effect = null;
			default: current_effect = null;
		}
	}
	
	public double getDrive() {
		return drive;
	}

	public void setDrive(double drive) {
		if(drive < 0)
			this.drive = 0.0;
		else if(drive >= 1.0)
			this.drive = 0.99;
		else
			this.drive = drive;
	}

	public int getDelayLength() {
		return delay_length;
	}

	public void setDelayLength(int delay_length) {
		if(delay_length < 0)
			delay_length = 0;
		else if(delay_length > 10)
			delay_length = Short.MAX_VALUE;
		else
			this.delay_length = (Short.MAX_VALUE / 10) * delay_length;
	}

	public double getDelayFeedback() {
		return delay_feedback;
	}

	public void setDelayFeedback(double delay_feedback) {
		if(delay_feedback < 0)
			delay_feedback = 0;
		else if(delay_feedback > 1)
			delay_feedback = 1;
		else
			this.delay_feedback = delay_feedback;
	}
}
