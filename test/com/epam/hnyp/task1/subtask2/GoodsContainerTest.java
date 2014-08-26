package com.epam.hnyp.task1.subtask2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask1.Guitar;
import com.epam.hnyp.task1.subtask1.Guitar.StringType;
import com.epam.hnyp.task1.subtask1.MusicInstrument;
import com.epam.hnyp.task1.subtask1.Sax;
import com.epam.hnyp.task1.subtask1.Trumpet;
import com.epam.hnyp.task1.subtask1.Violin;

public class GoodsContainerTest {

	private GoodsContainer<MusicInstrument> container;
	
	@Before
	public void before(){
		container = new GoodsContainer<>();
		container.add(new MusicInstrument());
		container.add(new Guitar("gibson", 1999, 6, "burst", StringType.METAL));
		container.add(new Violin());
		container.add(new Trumpet("aaa", 2002, "coper"));
		container.add(new Trumpet("aaa", 2005, "coper"));
		container.add(new Violin());
		container.add(new Guitar());
		container.add(new Guitar("yamaha", 1999, 6, "bright brown", StringType.NEULON));
		container.add(new Violin("aaa",2000, 4));
	}
	
	@Test
	public void testContains() {
		assertTrue(container.contains(new Violin()));
		assertFalse(container.contains(new Violin("aaa1", 2000, 4)));
	}

	@Test
	public void testToArray() {
		Object[] arr = container.toArray();
		assertTrue(arr.length == 9);
		assertTrue(((MusicInstrument)arr[8]).getVendor().equals("aaa"));
	}

	@Test
	public void testAddE() {
		MusicInstrument m = new Sax();
		assertTrue(container.size() == 9 && container.capacity() == GoodsContainer.DEFAULT_LENGTH);
		container.add(m);
		assertTrue(container.size() == 10 && container.capacity() == GoodsContainer.DEFAULT_LENGTH);
		assertTrue(container.get(9).equals(m));
		container.add(m);
		assertTrue(container.size() == 11 && 
				container.capacity() == GoodsContainer.DEFAULT_LENGTH + GoodsContainer.DEFAULT_EXTRA_LENGTH);
		container.add(m);
		assertTrue(container.size() == 12 && 
				container.capacity() == GoodsContainer.DEFAULT_LENGTH + GoodsContainer.DEFAULT_EXTRA_LENGTH);
	}

	@Test
	public void testRemoveObject() {
		assertFalse(container.remove(new Sax("qwerty", 2002, "bronze")));
		assertTrue(container.remove(new Violin()));
	}

	@Test
	public void testRemoveInt() {
		MusicInstrument m = new Sax();
		container.add(m);
		container.add(m);
		assertTrue(container.capacity() == container.DEFAULT_LENGTH + container.DEFAULT_EXTRA_LENGTH);
		container.remove(0);
		assertTrue(container.capacity() == container.DEFAULT_LENGTH);
		container.remove(0);
		assertTrue(container.capacity() == container.DEFAULT_LENGTH);
		try {
			container.remove(9);
			fail();
		} catch (IndexOutOfBoundsException e){ }
		try {
			container.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e){ }
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		GoodsContainer<MusicInstrument> c = new GoodsContainer<>();
		Guitar g1 = new Guitar("esp", 2003, 7, "black", StringType.METAL);
		Guitar g2 = new Guitar("ltd", 2000, 7, "black", StringType.METAL);
		Violin g3 = new Violin("123", 2009, 4);
		c.add(g1);
		c.add(g2);
		c.add(g3);
		int sz = container.size();
		container.addAll(c);
		assertTrue(container.size() == sz + 3);
		assertTrue(container.get(sz).equals(g1));
		assertTrue(container.get(container.size() - 1).equals(g3));
		
		//to reset container
		before();
		
		container.addAll(0, c);
		assertTrue(container.size() == sz + 3);
		assertTrue(container.get(0).equals(g1));
		assertTrue(container.get(2).equals(g3));
	}

	@Test
	public void testRemoveAll() {
		GoodsContainer<MusicInstrument> c = new GoodsContainer<>();
		MusicInstrument m1 = new Violin();
		MusicInstrument m2 = new Trumpet("aaa", 2002, "coper");
		MusicInstrument m3 = new Trumpet("aaa", 2005, "coper");
		c.add(m1);
		c.add(m2);
		c.add(m3);
		
		int sz = container.size();
		assertTrue(container.removeAll(c));
		
		assertTrue(sz - container.size() == 3);
		assertTrue(container.contains(m1));
		assertTrue(!container.contains(m2));
		assertTrue(!container.contains(m3));
	}

	@Test
	public void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntE() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastIndexOf() {
		fail("Not yet implemented");
	}

}
