package com.epam.hnyp.task2.subtask3.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public abstract class AbstractProductFactory {
	
//	private Map<String, MyKeyValue<String ,Class<? extends Product>>> prods = new LinkedHashMap<>();
//	{
//		prods.put("1",  new MyKeyValue<String, Class<? extends Product>>("simple product", Product.class));
//		prods.put("2",  new MyKeyValue<String, Class<? extends Product>>("vegetable", VegetableProduct.class));
//		prods.put("3",  new MyKeyValue<String, Class<? extends Product>>("drink", DrinkProduct.class));
//		prods.put("4",  new MyKeyValue<String, Class<? extends Product>>("sweet", SweetProduct.class));
//		prods.put("5",  new MyKeyValue<String, Class<? extends Product>>("cereal", CerealProduct.class));
//		prods.put("6",  new MyKeyValue<String, Class<? extends Product>>("weightable product", WeightableProduct.class));
//	}
	
	protected Map<String, Class<? extends Product>> products;
	
	protected Map<Class<?>, FieldReader> readers;

	public AbstractProductFactory(Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers) {
		this.products = products;
		this.readers = readers;
	}

	public Map<String, Class<? extends Product>> getProducts() {
		return new LinkedHashMap<>(products);
	}

	public abstract Product createProduct(Class<? extends Product> type) throws CreateProductException;
	
	public static class CreateProductException extends Exception {
		public CreateProductException() {
		}
		
		public CreateProductException(String message) {
			super(message);
		}
	}
}
