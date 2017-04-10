package com.igor.guitarproject.processor;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class FFTLowPassFilter implements Effect {

	// cutoff frequency
	private double low_pass;
	// input frequency
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
		
		// Pad with zeros.
		double[] padding = new double[min_power_of_2];
		for(int i = 0; i < signal.length; i++)
			padding[i] = signal[i];
		
		FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
		Complex[] fourier_transform = transformer.transform(padding, TransformType.FORWARD);
		
		// Frequency domain array.
		double[] frequency_domain = new double[fourier_transform.length];
		for(int i = 0; i < frequency_domain.length; i++)
			frequency_domain[i] = frequency * i / (double)fourier_transform.length;
		
		// Classifier array. 2s stay in, 0s are rejected by the filter.
		double[] keep_signal = new double[frequency_domain.length];
		keep_signal[0] = 1;
		for(int i = 1; i < frequency_domain.length; i++) {
			if(frequency_domain[i] < low_pass)
				keep_signal[i] = 2;
			else
				keep_signal[i] = 0;
		}
		
		// Filter the FFT.
		for(int i = 0; i < fourier_transform.length; i++)
			fourier_transform[i] = fourier_transform[i].multiply((double)keep_signal[i]);
		
		// Invert back to time domain.
		Complex[] reverse_fourier = transformer.transform(fourier_transform, TransformType.INVERSE);
		
		// Get the real data of the reverse.
		short[] filtered_signal = new short[signal.length];
		for(int i = 0; i < filtered_signal.length; i++) {
			filtered_signal[i] = (short) reverse_fourier[i].getReal();
		}
		
		return filtered_signal;
	}
}
