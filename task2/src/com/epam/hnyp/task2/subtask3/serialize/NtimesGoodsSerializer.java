package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Good;
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
	protected void serializeObjects(Collection<Good> goods,
			ObjectOutputStream oos) throws IOException {
		for (int i = 0; i < count; i++) {
			super.serializeObjects(goods, oos);
		}
	}
}
