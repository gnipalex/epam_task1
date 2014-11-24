package com.epam.hnyp.shop.capcha;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public interface Capcha {
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
	void drawCapcha(OutputStream output, int picHeight, int picWidth,
			int fontSize) throws IOException;
	/**
	 * Gets current generated capcha
	 * @return
	 */
	String getCapcha();
	/**
	 * Gets MIME type of capcha image which is being written to the stream by drawCapcha method
	 * @return
	 */
	String getMimeType();
	/**
	 * Gets capcha create time
	 * @return
	 */
	Date getCreatedTime();
}
