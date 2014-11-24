package com.epam.hnyp.shop.util.img;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ImgProvider {
	/**
	 * Reads an image from input stream
	 * @param is {@link InputStream} object
	 * @param mimeType
	 * @return {@link BufferedImage} object
	 * @throws IOException
	 */
	BufferedImage read(InputStream is) throws IOException;
	
	/**
	 * Reads the image from file system using predefined folder
	 * @param fileName image file name in predefined folder
	 * @return {@link ImageInfo} object containing read Image and image MIME type
	 * @throws IOException
	 */
	ImageInfo read(String fileName) throws IOException;
	
	/**
	 * Resizes image to new dimensions
	 * @param img {@link BufferedImage} object
	 * @param width desired width in px
	 * @param height desired height in px
	 * @return {@link BufferedImage} object
	 * @throws IllegalArgumentException if width or height is non positive value
	 */
	BufferedImage resizeImage(BufferedImage img, int width, int height);
	
	/**
	 * Writes image to output stream
	 * @param img {@link BufferedImage} object
	 * @param os {@link OutputStream} object
	 * @throws IOException
	 */
	void write(BufferedImage img, OutputStream os) throws IOException;
	
	/**
	 * Writes image to predefined folder in file system
	 * @param img {@link BufferedImage} object
	 * @param fileName image file name in predefined folder without extension
	 * @throws IOException
	 */
	void write(BufferedImage img, String fileName) throws IOException;
	
	/**
	 * Removes image from predefined folder
	 * @param fileName file name
	 */
	void remove(String fileName);
	
	/**
	 * Checks whether this image provider supports the mime type
	 * @param mimeType
	 * @return
	 */
	boolean supportsMimeType(String mimeType);
	
	/**
	 * 
	 * @return suffix of the file according to supported by implementation mime type 
	 */
	String getFileNameSuffix();
	
	/**
	 * Gets directory with images asociated with image provider
	 * @return
	 */
	String getDirectoryLocation();
}
