package com.epam.hnyp.task9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.epam.hnyp.task9.util.CapchaAwtJpegImpl;

public class ImgDrawTest {
	public static void main(String[] args) throws IOException {
		CapchaAwtJpegImpl capServ = new CapchaAwtJpegImpl(5);
		
		FileOutputStream fout = new FileOutputStream("img.jpeg");
		
		capServ.drawCapcha(fout, 100, 200, 40);
		
		fout.close();
		
	}
}
