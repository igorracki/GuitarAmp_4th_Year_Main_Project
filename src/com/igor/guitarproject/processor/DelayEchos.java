package com.igor.guitarproject.processor;

public class DelayEchos {

	private int delay_length;
	private double delay_feedback;
	private short[] delay_buffer;
	private short[] processed_buffer = new short[204];
	private int current_position;
	
	public DelayEchos(int delay_length, double delay_feedback) {
		this.delay_length = delay_length;
		this.delay_feedback = delay_feedback;
		
		delay_buffer = new short[Short.MAX_VALUE];
	}
	
	public short[] getEchos(short[] signal) {
		for(int i = 0; i < signal.length; i++) {
			processed_buffer[i] = delay_buffer[current_position];
			delay_buffer[current_position] += signal[i];
			delay_buffer[current_position] *= -delay_feedback;
			current_position++;
			current_position %= delay_length;
		}
		
		return processed_buffer;
	}
}
