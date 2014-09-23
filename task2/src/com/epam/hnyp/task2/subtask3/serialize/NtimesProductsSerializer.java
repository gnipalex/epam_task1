package com.epam.hnyp.task2.subtask3.serialize;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Product;

public class NtimesProductsSerializer extends ProductsSerializer {
	private int count;

	public NtimesProductsSerializer() {
		this.count = 1;
	}

	public NtimesProductsSerializer(int count) {
		this.count = count;
	}

	@Override
	protected void serializeObjects(Collection<Product> prods, ObjectOutputStream oos) throws IOException {
		for (int i = 0; i < count; i++) {
			super.serializeObjects(prods, oos);
		}
	}
}
