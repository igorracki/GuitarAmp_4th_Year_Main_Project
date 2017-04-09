package com.igor.guitarproject.processor;

public class Overdrive implements Effect {
	
	private double drive;
	
	private double k;
	private double a;
	private double x;
	
	public Overdrive(double drive) {
		this.drive = drive;
	}

	@Override
	public short[] applyEffect(short[] signal) {
		if(drive != 0) {
			for(int i = 0; i < signal.length; i++) {
				x = (double)(signal[i]) / Short.MAX_VALUE;
				a = Math.sin(drive * Math.PI / 2);
				k = 2 * a / (1 - a);
				x = (1 + k) * x / (1 + k * Math.abs(x));
				signal[i] = (short)(x * Short.MAX_VALUE);
			}
		}
		
		return signal;
	}
}
