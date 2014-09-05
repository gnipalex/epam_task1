package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.util.Cart;
import com.epam.hnyp.task2.subtask3.util.InMemoryStore;
import com.epam.hnyp.task2.subtask3.util.Store;

public class ConfigGrocery {
	public static final Store STORE = InMemoryStore.getInstance();
	public static Cart CART = new Cart();
}
