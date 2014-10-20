package com.epam.hnyp.task9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.epam.hnyp.task9.service.impl.CapchaServiceAwtImpl;

public class ImgDrawTest {
	public static void main(String[] args) throws IOException {
		CapchaServiceAwtImpl capServ = new CapchaServiceAwtImpl(new Random(System.currentTimeMillis()),
				5);
		
		FileOutputStream fout = new FileOutputStream("img.jpeg");
		
		capServ.drawCapcha(fout, 100, 200, 40);
		
		fout.close();
		
	}
}
