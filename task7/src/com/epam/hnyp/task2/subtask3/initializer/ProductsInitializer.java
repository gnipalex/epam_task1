package com.epam.hnyp.task2.subtask3.initializer;


import com.epam.hnyp.task2.subtask3.model.CerealProduct;
import com.epam.hnyp.task2.subtask3.model.DrinkProduct;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.SweetProduct;
import com.epam.hnyp.task2.subtask3.model.VegetableProduct;
import com.epam.hnyp.task2.subtask3.model.WeightableProduct;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class ProductsInitializer {
	public static void fillProducts(ProductsService productsService, long idStart) {
		Product g1 = new CerealProduct(idStart++, "sugar", 12, 2);
		productsService.add(g1);
		Product g2 = new DrinkProduct(idStart++, "milk", 11, 1);
		productsService.add(g2);
		Product g3 = new SweetProduct(idStart++, "vafles artek", 30, 0.2, "chocolate");
		productsService.add(g3);
		Product g4 = new VegetableProduct(idStart++, "potato", 4, 1);
		productsService.add(g4);
		Product g5 = new WeightableProduct(idStart++, "bread", 5, 0.7);
		productsService.add(g5);
		Product g6 = new DrinkProduct(idStart++, "cola", 12, 2);
		productsService.add(g6);
		Product g7 = new Product(idStart++, "sigarettes", 20);
		productsService.add(g7);
		Product g8 = new Product(idStart++, "butter", 15);
		productsService.add(g8);
	}
}
