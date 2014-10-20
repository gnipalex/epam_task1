package com.epam.hnyp.task9.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

import sun.awt.image.codec.JPEGImageEncoderImpl;

import com.epam.hnyp.task9.service.CapchaService;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CapchaServiceAwtImpl implements CapchaService {
	public static final String CAPCHA_CHARS = "abcdefghijkmnopqrstuvwxyz23456789";
	public static final String JPEG_MIME_TYPE = "image/jpeg";
	public static final int MAX_ANGLE = 40;

	private String capcha;
	private Random rand;
	private int length;
	private String uuid;
	
	/**
	 * Creates new capcha using specified random generator and specified length
	 * @param rand {@link Random} object to generate random numbers
	 * @param length capcha length in symbols
	 * @throws IllegalArgumentException if capcha length < 1
	 */
	public CapchaServiceAwtImpl(Random rand, int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length must be >= 1");
		}
		this.rand = rand;
		this.length = length;
		
		this.capcha = generateCapcha();
		this.uuid = UUID.randomUUID().toString();
	}

	private String generateCapcha() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < length; i++) {
			out.append(CAPCHA_CHARS.charAt(rand.nextInt(CAPCHA_CHARS.length())));
		}
		return out.toString();
	}
	
	/**
	 * Draws picture of capcha to {@link OutputStream}. Notice that the font size 
	 * and capcha height should be in proportion <br>
	 * picWidth >= fontSize * N, where N - length of capcha string
	 * @param output {@link OutputStream} to write picture of capcha
	 * @param picHeight capcha height in px
	 * @param picWidth capcha width in px
	 * @param fontSize symbol height and width in px
	 * @throws IOException if error occured while writing image to stream 
	 * @throws IllegalArgumentException if bad picture dimensions were specified,<br>
	 *  or font size < 1
	 */
	@Override
	public void drawCapcha(OutputStream output, int picHeight, int picWidth,
			int fontSize) throws IOException {
		if (picHeight < 1 || picWidth < 1) {
			throw new IllegalArgumentException("bad picture dimensions");
		}
		if (fontSize < 1) {
			throw new IllegalArgumentException("bad font size");
		}
		
		BufferedImage bufImg = new BufferedImage(picWidth, picHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = bufImg.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, picWidth, picHeight);
		
		Color[] colors = {Color.BLUE, Color.MAGENTA, Color.RED, Color.BLACK};

		g.setFont(new Font("serif", Font.PLAIN, fontSize));
		char[] text = capcha.toCharArray();
		
		int offsetY = (picHeight - fontSize)/2 + 3*fontSize/4;
		int offsetX = (picWidth - fontSize * text.length)/2 + fontSize/4;

		for (int i=0; i<text.length; i++, offsetX += fontSize) {
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(rand.nextInt(MAX_ANGLE) - MAX_ANGLE/2));
			g.setFont(g.getFont().deriveFont(at));
			g.setColor(colors[rand.nextInt(colors.length)]);
			g.drawChars(text, i, 1, offsetX, offsetY);
		}
		
		g.dispose();
		
		JPEGImageEncoder encoder = new JPEGImageEncoderImpl(output);
		encoder.encode(bufImg);
	}
	
	@Override
	public String getCapcha() {
		return capcha;
	}

	public int getLength() {
		return length;
	}
	
	@Override
	public String getUuid() {
		return uuid;
	}
	
	@Override
	public String getMimeType() {
		return JPEG_MIME_TYPE;
	}
}
