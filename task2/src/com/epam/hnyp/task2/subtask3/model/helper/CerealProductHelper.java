package com.epam.hnyp.task2.subtask3.model.helper;

import java.io.Serializable;

import com.epam.hnyp.task2.subtask3.model.CerealProduct;
import com.epam.hnyp.task2.subtask3.model.Product;

public class CerealProductHelper extends WeightableProductHelper {
	
	@Override
	protected Product newInstanceOfProduct() {
		return new CerealProduct();
	}
}
