package com.epam.hnyp.task9.service;

import java.io.IOException;
import java.io.OutputStream;

public interface CapchaService {
	void drawCapcha(OutputStream output, int picHeight, int picWidth,
			int fontSize) throws IOException;
	String getCapcha();
	String getUuid();
	String getMimeType();
}
