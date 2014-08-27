package com.epam.hnyp.task1.subtask2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ParameterizedIteratorTest {

	@Test
	public void test() {
		GoodsContainer<Integer> list = new GoodsContainer<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Condition<Integer> cond = new Condition<Integer>() {
			@Override
			public boolean satisfy(Integer item) {
				return item > 2;
			}
		};
		list.setIteratorCondition(cond);
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		assertTrue(iter.next() == 3);
		assertTrue(iter.hasNext());
		assertTrue(iter.next() == 4);
		assertFalse(iter.hasNext());
		try {
			iter.next();
			fail();
		} catch (NoSuchElementException e) { }
	}

}
