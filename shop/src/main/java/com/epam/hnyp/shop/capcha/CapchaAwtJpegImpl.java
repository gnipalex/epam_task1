package com.epam.hnyp.shop.capcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import sun.awt.image.codec.JPEGImageEncoderImpl;

import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CapchaAwtJpegImpl implements Capcha, Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_CAPCHA_LENGTH = 4;
	public static final Random RANDOM = new Random(System.currentTimeMillis());
	
	public static final String CAPCHA_CHARS = "abcdefghijkmnopqrstuvwxyz23456789";
	public static final String JPEG_MIME_TYPE = "image/jpeg";
	public static final int MAX_ANGLE = 40;

	private String capcha;
	private int length;
	private Date createdTime = new Date();
	
	public CapchaAwtJpegImpl() {
		this(DEFAULT_CAPCHA_LENGTH); 
	}
	
	/**
	 * Creates new capcha using specified random generator and specified length
	 * @param rand {@link Random} object to generate random numbers
	 * @param length capcha length in symbols
	 * @throws IllegalArgumentException if capcha length < 1
	 */
	public CapchaAwtJpegImpl(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length must be >= 1");
		}
		this.length = length;
		generateCapcha();
	}

	private void generateCapcha() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < length; i++) {
			out.append(CAPCHA_CHARS.charAt(RANDOM.nextInt(CAPCHA_CHARS.length())));
		}
		this.capcha = out.toString();
	}
	
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
			at.rotate(Math.toRadians(RANDOM.nextInt(MAX_ANGLE) - MAX_ANGLE/2));
			g.setFont(g.getFont().deriveFont(at));
			g.setColor(colors[RANDOM.nextInt(colors.length)]);
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
	
	@Override
	public String getMimeType() {
		return JPEG_MIME_TYPE;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public Date getCreatedTime() {
		return createdTime;
	}
}
