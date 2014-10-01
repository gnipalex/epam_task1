package com.epam.hnyp.task2.subtask3.model.helper;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.VegetableProduct;

public class VegetableProductHelper extends WeightableProductHelper {
	
	@Override
	protected Product newInstanceOfProduct() {
		return new VegetableProduct();
	}

}
