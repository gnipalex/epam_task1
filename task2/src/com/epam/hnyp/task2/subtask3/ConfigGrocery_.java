package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.util.InMemoryStore;
import com.epam.hnyp.task2.subtask3.util.Store;
import com.epam.hnyp.task2.subtask3.util.impl.CartImpl;

public class ConfigGrocery_ {
	public static Store STORE = InMemoryStore.getInstance();
	public static CartImpl CART = new CartImpl();
	
}
