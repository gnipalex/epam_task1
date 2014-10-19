package com.epam.hnyp.task9;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.awt.image.codec.JPEGImageEncoderImpl;

import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgDrawTest {
	public static void main(String[] args) throws IOException {
		BufferedImage bufImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufImg.getGraphics();
		
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(30));
		Font font = new Font("serif", Font.PLAIN, 20).deriveFont(at);
		
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.BLACK);
		g.drawString("Stroka", 10, 15);
		//at.rotate()
		at.rotate(Math.toRadians(-30));
		font = font.deriveFont(at);
		g.setFont(font);
		g.drawString("qwerty", 10, 50);
		g.dispose();
		
		FileOutputStream fout = new FileOutputStream("img.jpeg");
		
		JPEGImageEncoder encoder = new JPEGImageEncoderImpl(fout);
		encoder.encode(bufImg);
		
		fout.close();
		
	}
}
