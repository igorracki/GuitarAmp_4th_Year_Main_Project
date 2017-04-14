package com.igor.guitarproject.controller;

import com.igor.guitarproject.processor.Delay;
import com.igor.guitarproject.processor.Effect;
import com.igor.guitarproject.processor.FFTLowPassFilter;
import com.igor.guitarproject.processor.Overdrive;

/**
 * Class controlling all available effects.
 * 
 * @author Igor
 *
 */
public class EffectsController {

	private AudioController audio_c;
	
	// Parameters for effects.
	private double drive;
	private int delay_length;
	private double delay_feedback;
	private double low_pass;
	private double frequency;
	
	// Effects.
	private Effect current_effect;
	private Effect filter_effect;
	
	public EffectsController(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
	/**
	 * Check if an effect is currently active.
	 * @return boolean isEffectActive
	 */
	public boolean isEffectActive() {
		if(current_effect == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Check if a filter is currently active.
	 * @return boolean isFilterActive
	 */
	public boolean isFilterActive() {
		if(filter_effect == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Update filter parameters.
	 */
	public void updateFilter() {
		filter_effect.updateValues(this.low_pass, this.frequency);
	}
	
	/**
	 * Update overdrive effect parameters.
	 */
	public void updateOverdrive() {
		current_effect.updateValues(this.drive, 0);
	}
	
	/**
	 * Update delay effect parameters.
	 */
	public void updateDelay() {
		current_effect.updateValues(this.delay_length, this.delay_feedback);
	}

	/*********************** 	GETTERS & SETTERS 	***********************/
	public Effect getCurrentEffect() {
		return current_effect;
	}
	
	public Effect getCurrentFilter() {
		return filter_effect;
	}
	
	/**
	 * Set current effect based on the user input.
	 * @param String effect
	 */
	public void setCurrentEffect(String effect) {
		switch(effect) {
			case "overdrive": current_effect = new Overdrive(drive); break;
			case "delay": current_effect = new Delay(delay_length, delay_feedback); break;
			case "none": current_effect = null;
			default: current_effect = null;
		}
	}
	
	/**
	 * Set current filter based on the user input.
	 * @param String filter
	 */
	public void setCurrentFilter(String filter) {
		switch(filter) {
			case "lowpass": filter_effect = new FFTLowPassFilter(low_pass, frequency); break;
			case "none": filter_effect = null;
			default: filter_effect = null;
		}
	}
	
	public double getDrive() {
		return drive;
	}

	public void setDrive(double drive) {
		if(drive <= 0)
			this.drive = 0.1;
		else if(drive >= 1.0)
			this.drive = 0.99;
		else
			this.drive = drive;
	}

	public int getDelayLength() {
		return delay_length;
	}

	public void setDelayLength(int delay_length) {
		if(delay_length <= 0)
			this.delay_length = (Short.MAX_VALUE / 10) * 1;
		else if(delay_length > 10)
			this.delay_length = Short.MAX_VALUE;
		else
			this.delay_length = (Short.MAX_VALUE / 10) * delay_length;
	}

	public double getDelayFeedback() {
		return delay_feedback;
	}

	public void setDelayFeedback(double delay_feedback) {
		if(delay_feedback < 0)
			this.delay_feedback = 0;
		else if(delay_feedback > 1)
			this.delay_feedback = 1;
		else
			this.delay_feedback = delay_feedback;
	}

	public double getLowPass() {
		return low_pass;
	}

	public void setLowPass(double low_pass) {
		this.low_pass = low_pass;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
}
