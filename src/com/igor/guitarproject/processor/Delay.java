package com.igor.guitarproject.processor;

public class Delay implements Effect {

	private int delay_length;
	private double delay_feedback;
	private short[] processed_buffer = new short[204];
	private DelayEchos echos;
	
	
	public Delay(int delay_length, double delay_feedback) {
		this.delay_length = delay_length;
		this.delay_feedback = delay_feedback;
		echos = new DelayEchos(delay_length, delay_feedback);
	}
	
	@Override
	public short[] applyEffect(short[] signal) {
		processed_buffer = echos.getEchos(signal);
		for(int i = 0; i < signal.length; i++) {
			signal[i] = processed_buffer[i];
		}
		return signal;
	}

	@Override
	public void updateValues(double value_one, double value_two) {
		this.delay_length = (int)value_one;
		this.delay_feedback = value_two;
		echos.updateValues((int)value_one, value_two);
	}
}
