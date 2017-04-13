package com.igor.guitarproject.processor;

public interface Effect {
	short[] applyEffect(short[] signal);
	
	void updateValues(double value_one, double value_two);
}
