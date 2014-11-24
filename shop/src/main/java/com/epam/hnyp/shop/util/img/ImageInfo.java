package com.epam.hnyp.shop.util.img;

import java.awt.image.BufferedImage;

public class ImageInfo {
	private BufferedImage image;
	private String mimeType;

	public ImageInfo(BufferedImage image, String mimeType) {
		this.image = image;
		this.mimeType = mimeType;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
