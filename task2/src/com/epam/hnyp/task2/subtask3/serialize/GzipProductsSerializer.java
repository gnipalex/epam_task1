package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class GzipProductsSerializer extends ProductsSerializer {
	
	@Override
	protected void deserialize(ProductsService prodServ, InputStream stream)
			throws IOException {
		GZIPInputStream gzipStream = new GZIPInputStream(stream);
		super.deserialize(prodServ, gzipStream);
	}
	
	@Override
	protected void serialize(ProductsService prodServ, OutputStream stream)
			throws IOException {
		GZIPOutputStream gzipStream = new GZIPOutputStream(stream);
		super.serialize(prodServ, gzipStream);
		gzipStream.finish();
	}
}
