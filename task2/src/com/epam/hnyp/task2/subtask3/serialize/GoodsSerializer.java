package com.epam.hnyp.task2.subtask3.serialize;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class GoodsSerializer {
	
	public void serialize(GoodsService goodServ, File file) throws IOException {
		OutputStream stream = new FileOutputStream(file);
		try {
			serialize(goodServ, stream);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	protected void serialize(GoodsService goodServ, OutputStream stream) throws IOException {
		ObjectOutputStream objectStream = new ObjectOutputStream(stream);
		Collection<Good> goods = goodServ.getAll();
		for (Good g : goods) {
			objectStream.writeObject(g);
		}
	}
	
	public void deserialize(GoodsService goodServ, File file) throws IOException {
		InputStream stream = new FileInputStream(file);
		try {
			deserialize(goodServ, stream);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	protected void deserialize(GoodsService goodServ, InputStream stream) throws IOException {
		ObjectInputStream objectStream = new ObjectInputStream(stream);
		Object obj = null;
		while (true) {
			try {
				obj = objectStream.readObject();
				if (obj instanceof Good) {
					goodServ.add((Good)obj);
				}
			} catch (ClassNotFoundException e) {
				continue;
			} catch (EOFException e) {
				return;
			}
		} 
	}
}
