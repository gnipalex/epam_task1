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

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class ProductsSerializer {
	
	public void serialize(ProductsService prodServ, File file) throws IOException {
		OutputStream stream = new FileOutputStream(file);
		try {
			serialize(prodServ, stream);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	protected void serialize(ProductsService prodServ, OutputStream stream) throws IOException {
		ObjectOutputStream objectStream = new ObjectOutputStream(stream);
		Collection<Product> prods = prodServ.getAll();
		serializeObjects(prods, objectStream);
	}
	
	protected void serializeObjects(Collection<Product> prods, ObjectOutputStream oos) throws IOException {
		for (Product g : prods) {
			oos.writeObject(g);
		}
	}
	
	public void deserialize(ProductsService prodServ, File file) throws IOException {
		InputStream stream = new FileInputStream(file);
		try {
			deserialize(prodServ, stream);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	protected void deserialize(ProductsService prodServ, InputStream stream) throws IOException {
		ObjectInputStream objectStream = new ObjectInputStream(stream);
		Object obj = null;
		while (true) {
			try {
				obj = objectStream.readObject();
				if (obj instanceof Product) {
					prodServ.add((Product)obj);
				}
			} catch (ClassNotFoundException e) {
				continue;
			} catch (EOFException e) {
				return;
			}
		} 
	}
}
