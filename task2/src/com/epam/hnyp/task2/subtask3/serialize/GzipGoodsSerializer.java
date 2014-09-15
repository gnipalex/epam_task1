package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class GzipGoodsSerializer extends GoodsSerializer {
	
	@Override
	protected void deserialize(GoodsService goodServ, InputStream stream)
			throws IOException {
		GZIPInputStream gzipStream = new GZIPInputStream(stream);
		super.deserialize(goodServ, gzipStream);
	}
	
	@Override
	protected void serialize(GoodsService goodServ, OutputStream stream)
			throws IOException {
		GZIPOutputStream gzipStream = new GZIPOutputStream(stream);
		super.serialize(goodServ, gzipStream);
		gzipStream.finish();
	}
}
