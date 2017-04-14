package com.igor.guitarproject.processor;

/**
 * Interface effects.
 * Contains methods that each effect should perform.
 * @author Igor
 *
 */
public interface Effect {
	// Apply the effect to current signal.
	short[] applyEffect(short[] signal);
	// Update the parameters of the effect.
	void updateValues(double value_one, double value_two);
}
