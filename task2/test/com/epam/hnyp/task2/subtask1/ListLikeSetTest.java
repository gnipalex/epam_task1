package com.epam.hnyp.task2.subtask1;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ListLikeSetTest {

	private ListLikeSet<Integer> list; 
	
	@Before
	public void before() {
		list = new ListLikeSet<>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
	}
	
	@Test
	public void testAdd() {
		assertTrue(list.add(6));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddEDuplicate() {
		list.add(1);
	}

	@Test
	public void testAddAtIndex() {
		list.add(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAtIndexDuplicate() {
		list.add(0, 1);
	}

	@Test
	public void testAddAllAtIndex() {
		List<Integer> c1 = new ArrayList<>();
		c1.add(6);
		c1.add(7);
		assertTrue(list.addAll(0 , c1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAllAtIndexDuplicate() {
		List<Object> listToAdd = new ArrayList<Object>();
		Object duplicate = new Object();
		listToAdd.add(duplicate);
		listToAdd.add(duplicate);

		ListLikeSet<Object> listToTest = new ListLikeSet<>();
		listToTest.addAll(listToAdd);
	}

	@Test
	public void testSetAtIndex() {
		assertTrue(list.set(0, 0).equals(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetAtIndexDuplicate() {
		list.set(0, 3);
	}

}
