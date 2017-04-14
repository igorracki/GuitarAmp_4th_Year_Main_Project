package com.igor.guitarproject.processor;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * Low Pass Filter.
 * Implementation of Fast Fourier Transform to achieve Low-Pass filtering.
 * @author Igor
 *
 */
public class FFTLowPassFilter implements Effect {

	// The filter cutoff frequency.
	private double low_pass;
	// The input frequency of the signal.
	private double frequency;
	
	public FFTLowPassFilter(double low_pass, double frequency) {
		this.low_pass = low_pass;
		this.frequency = frequency;
	}

	@Override
	public short[] applyEffect(short[] signal) {
		// Powers of 2 for Apache FFT.
		int min_power_of_2 = 1;
		while(min_power_of_2 < signal.length) {
			min_power_of_2 = 2 * min_power_of_2;
		}
		
		// Pad the signal with zeros.
		double[] padding = new double[min_power_of_2];
		for(int i = 0; i < signal.length; i++)
			padding[i] = signal[i];
		
		FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
		Complex[] fourier_transform = transformer.transform(padding, TransformType.FORWARD);
		
		// Frequency domain array.
		double[] frequency_domain = new double[fourier_transform.length];
		for(int i = 0; i < frequency_domain.length; i++)
			frequency_domain[i] = frequency * i / (double)fourier_transform.length;
		
		// Classifier array. 
		// Signal of value 2 remains, 0 is rejected by the filter.
		double[] keep_signal = new double[frequency_domain.length];
		keep_signal[0] = 1;
		for(int i = 1; i < frequency_domain.length; i++) {
			if(frequency_domain[i] < low_pass)
				keep_signal[i] = 2;
			else
				keep_signal[i] = 0;
		}
		
		// Filter the FFT signal.
		for(int i = 0; i < fourier_transform.length; i++)
			fourier_transform[i] = fourier_transform[i].multiply((double)keep_signal[i]);
		
		// Convert back to time domain.
		Complex[] reverse_fourier = transformer.transform(fourier_transform, TransformType.INVERSE);
		
		// Store the real data of the signal in time domain.
		short[] filtered_signal = new short[signal.length];
		for(int i = 0; i < filtered_signal.length; i++) {
			filtered_signal[i] = (short) reverse_fourier[i].getReal();
		}
		
		return filtered_signal;
	}

	@Override
	public void updateValues(double value_one, double value_two) {
		this.low_pass = value_one;
		this.frequency = value_two;
	}

}
