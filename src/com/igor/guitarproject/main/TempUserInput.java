package com.igor.guitarproject.main;

import java.util.Scanner;

import com.igor.guitarproject.controller.AudioController;

public class TempUserInput implements Runnable {

	private AudioController audio_c;
	
	public TempUserInput(AudioController audio_c) {
		this.audio_c = audio_c;
	}
	
	@Override
	public void run() {
		System.out.println("\tPress '1' for Overdrive. ");
		System.out.println("\tPress '2' for Delay. ");
		System.out.println("\tPress '3' for Low Pass Filter. ");
		while(true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if(input.equals("1")) {
				if(audio_c.getEffectsController().isEffectActive()) {
					audio_c.getEffectsController().setCurrentEffect("none");
				} else {
					System.out.println("\tEnter Overdrive.");
					Scanner sc2 = new Scanner(System.in);
					double drive = sc2.nextDouble();
					audio_c.getEffectsController().setDrive(drive);
					audio_c.getEffectsController().setCurrentEffect("overdrive");
				}
			} else if(input.equals("2")) {
				if(audio_c.getEffectsController().isEffectActive()) {
					audio_c.getEffectsController().setCurrentEffect("none");
				} else {
					System.out.println("Enter Delay Length 0 - 10");
					Scanner sc2 = new Scanner(System.in);
					int delay_length = sc2.nextInt();
					System.out.println("Enter Delay Feedback 0.0 - 1.0");
					Scanner sc3 = new Scanner(System.in);
					double delay_feedback = sc3.nextDouble();
					audio_c.getEffectsController().setDelayLength(delay_length);
					audio_c.getEffectsController().setDelayFeedback(delay_feedback);
					audio_c.getEffectsController().setCurrentEffect("delay");
				}
			} else if(input.equals("3")) {
				if(audio_c.getEffectsController().isFilterActive()) {
					audio_c.getEffectsController().setCurrentFilter("none");
				} else {
					System.out.println("Enter Cutoff Freq.");
					Scanner sc2 = new Scanner(System.in);
					// 500 optimal
					double low_pass = sc2.nextDouble();
					System.out.println("Enter in freq.");
					Scanner sc3 = new Scanner(System.in);
					// 5000 optimal
					double frequency = sc3.nextDouble();
					audio_c.getEffectsController().setLowPass(low_pass);
					audio_c.getEffectsController().setFrequency(frequency);
					audio_c.getEffectsController().setCurrentFilter("lowpass");
				}
			}
		}
	}

}
