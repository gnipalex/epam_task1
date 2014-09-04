package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.util.Cart;
import com.epam.hnyp.task2.subtask3.util.InMemoryStore;
import com.epam.hnyp.task2.subtask3.util.Store;

public class Test {
	public static void main(String[] args) {
		Store store = InMemoryStore.getInstance();
		Cart myCart = new Cart();
		myCart.add(store.get(1));
	}
	
	
}
