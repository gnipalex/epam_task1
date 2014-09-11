package com.epam.hnyp.task2.subtask3.util.notused;

import com.epam.hnyp.task2.subtask3.util.impl.CartImpl;

public class ConfigGrocery_ {
	public static Store STORE = InMemoryStore.getInstance();
	public static CartImpl CART = new CartImpl();
	
}
