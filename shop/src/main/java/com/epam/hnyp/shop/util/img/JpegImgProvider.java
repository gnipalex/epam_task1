package com.epam.hnyp.shop.util.img;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;

import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReaderSpi;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import com.sun.imageio.plugins.jpeg.JPEGImageWriterSpi;

/**
 * Provider to work with JPEG images
 * @author Oleksandr_Hnyp
 *
 */
public class JpegImgProvider implements ImgProvider {
	public static final String MIME_TYPE = "image/jpeg";
	public static final String FILE_EXT = "jpg";
	
	private String diskFolder;

	public JpegImgProvider(String diskFolder) {
		this.diskFolder = diskFolder;
	}

	@Override
	public BufferedImage read(InputStream is) throws IOException {
		ImageReader reader = new JPEGImageReader(new JPEGImageReaderSpi());
		reader.setInput(ImageIO.createImageInputStream(is));
		BufferedImage bufImg = reader.read(0);
		reader.dispose();
		return bufImg;
	}

	@Override
	public ImageInfo read(String fileName) throws IOException {
		try (FileInputStream fis = new FileInputStream(diskFolder + File.separator + fileName)) {
			BufferedImage image = read(fis);
			return new ImageInfo(image, MIME_TYPE);
		} 
	}

	@Override
	public BufferedImage resizeImage(BufferedImage img, int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		g.dispose();
		return bi;
	}

	@Override
	public void write(BufferedImage img, OutputStream os) throws IOException {
		ImageWriter writer = new JPEGImageWriter(new JPEGImageWriterSpi());
		writer.setOutput(ImageIO.createImageOutputStream(os));
		writer.write(img);
		writer.dispose();
	}

	@Override
	public void write(BufferedImage img, String fileName) throws IOException{
		try (FileOutputStream fos = new FileOutputStream(diskFolder + File.separator + fileName)) {
			write(img, fos);
		}
	}

	@Override
	public void remove(String fileName) {
		File file = new File(diskFolder + File.separator + fileName);
		file.delete();
	}

	@Override
	public boolean supportsMimeType(String mimeType) {
		return mimeType.equals(MIME_TYPE);
	}
	
	@Override
	public String getFileNameSuffix() {
		return FILE_EXT;
	}

	@Override
	public String getDirectoryLocation() {
		return diskFolder;
	}
}
