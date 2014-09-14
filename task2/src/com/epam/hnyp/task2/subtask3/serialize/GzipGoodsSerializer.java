package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class GzipGoodsSerializer extends GoodsSerializer {
	
	@Override
	protected void deserialize(GoodsService goodServ, InputStream stream)
			throws IOException {
		// TODO Auto-generated method stub
		super.deserialize(goodServ, stream);
	}
	
	@Override
	protected void serialize(GoodsService goodServ, OutputStream stream)
			throws IOException {
		// TODO Auto-generated method stub
		super.serialize(goodServ, stream);
	}
}
