package com.epam.hnyp.task2.subtask3.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator;
import com.epam.hnyp.task2.subtask3.creator.ReflectionProductCreator;
import com.epam.hnyp.task2.subtask3.creator.ReflectionRandomProductCreator;
import com.epam.hnyp.task2.subtask3.creator.SimpleProductCreator;
import com.epam.hnyp.task2.subtask3.creator.SimpleRandomProductCreator;
import com.epam.hnyp.task2.subtask3.model.CerealProduct;
import com.epam.hnyp.task2.subtask3.model.DrinkProduct;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.SweetProduct;
import com.epam.hnyp.task2.subtask3.model.VegetableProduct;
import com.epam.hnyp.task2.subtask3.model.WeightableProduct;
import com.epam.hnyp.task2.subtask3.model.helper.CerealProductHelper;
import com.epam.hnyp.task2.subtask3.model.helper.DrinkProductHelper;
import com.epam.hnyp.task2.subtask3.model.helper.HelperNoReflection;
import com.epam.hnyp.task2.subtask3.model.helper.ProductHelper;
import com.epam.hnyp.task2.subtask3.model.helper.SweetProductHelper;
import com.epam.hnyp.task2.subtask3.model.helper.VegetableProductHelper;
import com.epam.hnyp.task2.subtask3.model.helper.WeightableProductHelper;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.DoubleConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.IntConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.LongConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.StringConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.DoubleRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.IntRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.LongRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.StringRandomFieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ProductCreatorFactory {
	
	public static final int RANDOM_SYMBOLS_COUNT = 5;
	public static final int RANDOM_DOUBLE_MAX = 100;
	public static final int RANDOM_INT_MAX = 100;
	
	private static Map<String, Class<? extends Product>> getMessageProduct() {
		Map<String, Class<? extends Product>> prods = new LinkedHashMap<>();
		prods.put("simple product", Product.class);
		prods.put("vegetable", VegetableProduct.class);
		prods.put("drink", DrinkProduct.class);
		prods.put("sweet", SweetProduct.class);
		prods.put("cereal", CerealProduct.class);
		prods.put("weightable product", WeightableProduct.class);
		return prods;
	}
	
	private static Map<Class<?>, FieldReader> getConsoleReaders(IOProvider ioProvider) {
		Map<Class<?>, FieldReader> readers = new HashMap<>();
		readers.put(String.class, new StringConsoleFieldReader(ioProvider));
		readers.put(Double.class, new DoubleConsoleFieldReader(ioProvider));
		readers.put(Integer.class, new IntConsoleFieldReader(ioProvider));
		readers.put(Long.class, new LongConsoleFieldReader(ioProvider));
		return readers;
	}
	
	private static Map<Class<?>, FieldReader> getRandomReaders() {
		Map<Class<?>, FieldReader> readers = new HashMap<>();
		Random rand = new Random(System.currentTimeMillis());
		readers.put(String.class, new StringRandomFieldReader(RANDOM_SYMBOLS_COUNT, rand));
		readers.put(Double.class, new DoubleRandomFieldReader(RANDOM_DOUBLE_MAX, rand));
		readers.put(Integer.class, new IntRandomFieldReader(RANDOM_INT_MAX, rand));
		readers.put(Long.class, new LongRandomFieldReader(rand));
		return readers;
	}
	
	private static Map<Class<? extends Product>, HelperNoReflection> getHelpers() {
		Map<Class<? extends Product>, HelperNoReflection> helpers = new HashMap<>();
		helpers.put(Product.class, new ProductHelper());
		helpers.put(DrinkProduct.class, new DrinkProductHelper());
		helpers.put(CerealProduct.class, new CerealProductHelper());
		helpers.put(SweetProduct.class, new SweetProductHelper());
		helpers.put(VegetableProduct.class, new VegetableProductHelper());
		helpers.put(WeightableProduct.class, new WeightableProductHelper());
		return helpers;
	}

	public static AbstractProductCreator newReflectionProductCreator(IOProvider ioProvider, ResourceBundle resources) {
		return new ReflectionProductCreator(getMessageProduct(), getConsoleReaders(ioProvider), ioProvider, resources);
	}
	
	public static AbstractProductCreator newReflectionRandomProductCreator(IOProvider ioProvider, ResourceBundle resources) {
		return new ReflectionRandomProductCreator(getMessageProduct(), getRandomReaders(), ioProvider, resources);
	}
	
	public static AbstractProductCreator newSimpleProductCreator(IOProvider ioProvider) {
		return new SimpleProductCreator(getMessageProduct(), getConsoleReaders(ioProvider), getHelpers(), ioProvider);
	}
	
	public static AbstractProductCreator newSimpleRandomProductCreator(IOProvider ioProvider) {
		return new SimpleRandomProductCreator(getMessageProduct(), getRandomReaders(), getHelpers(), ioProvider);
	}
}
