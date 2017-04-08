package com.igor.guitarproject.controller;

import com.igor.guitarproject.processor.Effect;
import com.igor.guitarproject.processor.Overdrive;

public class EffectsController {

	private AudioController audio_c;
	
	// Parameters for effects.
	private double drive;
	
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
}
