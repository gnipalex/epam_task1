package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.OutputStream;

import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class NtimesGoodsSerializer extends GoodsSerializer {
	private int count;

	public NtimesGoodsSerializer() {
		this.count = 1;
	}

	public NtimesGoodsSerializer(int count) {
		this.count = count;
	}

	@Override
	protected void serialize(GoodsService goodServ, OutputStream stream)
			throws IOException {
		for (int i = 0; i < count; i++) {
			super.serialize(goodServ, stream);
		}
	}
}
