package com.igor.guitarproject.processor;

/**
 * Delay Effect.
 * Creates a time delayed signal with echos.
 * @author Igor
 *
 */
public class Delay implements Effect {

	// Time of the delay represented as an integer.
	private int delay_length;
	
	// Modifies the volume of the output signal (attenuation).
	// The attenuation has an effect on the amount of echos that are generated.
	private double delay_feedback;
	
	private short[] delayed_signal;
	private short[] processed_signal = new short[204];
	private int current_position;
	
	/**
	 * Initialize the effect.
	 * @param int delay_length
	 * @param double delay_feedback
	 */
	public Delay(int delay_length, double delay_feedback) {
		this.delay_length = delay_length;
		this.delay_feedback = delay_feedback;
		
		delayed_signal = new short[Short.MAX_VALUE];
	}
	
	/**
	 * Returns the input signal modified with a series of echos
	 * and a time delay added to the input.
	 */
	@Override
	public short[] applyEffect(short[] signal) {
		for(int i = 0; i < signal.length; i++) {
			processed_signal[i] = delayed_signal[current_position];
			delayed_signal[current_position] += signal[i];
			delayed_signal[current_position] *= -delay_feedback;
			current_position++;
			current_position %= delay_length;
		}

		return processed_signal;
	}

	@Override
	public void updateValues(double value_one, double value_two) {
		this.delay_length = (int)value_one;
		this.delay_feedback = value_two;
	}
}
