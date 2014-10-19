package com.epam.hnyp.task9.service;

import java.io.OutputStream;
import java.util.Random;

public class CapchaService {
	public static final String CAPCHA_CHARS = "abcdefghijkmnopqrstuvwxyz23456789";

	private String capcha;
	private Random rand;
	private int length;

	/**
	 * 
	 * @param rand
	 * @param length
	 * @throws IllegalArgumentException
	 *             if length < 1
	 */
	public CapchaService(Random rand, int length) {
		if (length < 1) {
			throw new IllegalArgumentException();
		}
		this.rand = rand;
		this.length = length;

		this.capcha = generateCapcha();

	}

	private String generateCapcha() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < length; i++) {
			out.append(CAPCHA_CHARS.charAt(rand.nextInt(CAPCHA_CHARS.length())));
		}
		return out.toString();
	}
	
	public void drawCapcha(OutputStream output, int height, int width) {
		
	}

	public String getCapcha() {
		return capcha;
	}

	public int getLength() {
		return length;
	}

}
