package com.igor.guitarproject.processor;

public interface Effect {
	short[] applyEffect(short[] signal, int signal_size);
}
