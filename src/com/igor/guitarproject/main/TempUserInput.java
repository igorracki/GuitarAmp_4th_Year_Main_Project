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
			}
		}
	}

}
