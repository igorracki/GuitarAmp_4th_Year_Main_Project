package com.igor.guitarproject.processor;

/**
 * Overdrive Effect.
 * Distorts the input signal to create an overdriven sound.
 * @author Igor
 *
 */
public class Overdrive implements Effect {
	
	// Represents the clipping of the sound.
	// Modifies the shape of the signal's wave. 
	// Low drive values create soft-clipping, altering the wave to remain a distorted sinusoidal function.
	// High drive values create hard-clipping, altering the wave to have sharp edges (square wave). 
	private double drive;
	
	private double k;
	private double a;
	private double x;
	
	/**
	 * Initialize the effect.
	 * @param double drive
	 */
	public Overdrive(double drive) {
		this.drive = drive;
	}

	@Override
	public short[] applyEffect(short[] signal) {
		for(int i = 0; i < signal.length; i++) {
			x = (double)(signal[i]) / Short.MAX_VALUE;
			a = Math.sin(drive * Math.PI / 2);
			k = 2 * a / (1 - a);
			x = (1 + k) * x / (1 + k * Math.abs(x));
			signal[i] = (short)(x * Short.MAX_VALUE);
		}
		
		return signal;
	}

	@Override
	public void updateValues(double value_one, double value_two) {
		this.drive = value_one;
	}
}
