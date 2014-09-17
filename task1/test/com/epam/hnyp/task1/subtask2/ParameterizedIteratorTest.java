package com.epam.hnyp.task1.subtask2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class ParameterizedIteratorTest {

	private GoodsList<Integer> list;
	
	@Before
	public void before() {
		list = new GoodsList<>();
		list.add(1);
		list.add(2);
		list.add(3);
	}
	
	@Test
	public void testCondition() {
		Condition<Integer> cond = new Condition<Integer>() {
			@Override
			public boolean satisfy(Integer item) {
				return item > 1;
			}
		};
		list.setIteratorCondition(cond);
		int count = 0;
		for (Integer i : list) {
			count++;
		}
		assertTrue(count == 2);
	}
	
	@Test
	public void testWithoutCondition() {
		int count = 0;
		for (Integer i : list) {
			count++;
		}
		assertTrue(count == list.size());
	}
	
	@Test
	public void testHasNext() {
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
	}
	
	@Test
	public void testHasNextDoesNotShiftCursor() {
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(1));
	}
	
	@Test
	public void testNext() {
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.next().equals(1));
		assertTrue(iter.next().equals(2));
		assertTrue(iter.next().equals(3));
	}
	
	@Test
	public void testNextNoSuchElementException(){
		Iterator<Integer> iter = list.iterator();
		iter.next();
		iter.next();
		iter.next();
		try {
			iter.next();
			fail();
		} catch (NoSuchElementException e) {}
	}
	
	
	
	
}
