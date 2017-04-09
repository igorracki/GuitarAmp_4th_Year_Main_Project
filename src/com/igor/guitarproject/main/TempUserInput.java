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
		while(true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if(input.equals("1")) {
				if(audio_c.getEffectsController().isEffectActive()) {
					audio_c.getEffectsController().setCurrentEffect("none");
				} else {
					System.out.println("\tEnter Overdrive.");
					Scanner sc2 = new Scanner(System.in);
					double drive = sc.nextDouble();
					audio_c.getEffectsController().setDrive(drive);
					audio_c.getEffectsController().setCurrentEffect("overdrive");
				}
			} else if(input.equals("2")) {
				if(audio_c.getEffectsController().isEffectActive()) {
					audio_c.getEffectsController().setCurrentEffect("none");
				} else {
					System.out.println("Enter Delay Length 0 - 10");
					Scanner sc2 = new Scanner(System.in);
					int delay_length = sc.nextInt();
					System.out.println("Enter Delay Feedback 0.0 - 1.0");
					Scanner sc3 = new Scanner(System.in);
					double delay_feedback = sc.nextDouble();
					audio_c.getEffectsController().setDelayLength(delay_length);
					audio_c.getEffectsController().setDelayFeedback(delay_feedback);
					audio_c.getEffectsController().setCurrentEffect("delay");
				}
			}
		}
	}

}
