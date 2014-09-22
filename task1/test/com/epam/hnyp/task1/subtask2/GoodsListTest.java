package com.epam.hnyp.task1.subtask2;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.task1.subtask1.Guitar;
import com.epam.hnyp.task1.subtask1.Guitar.StringType;
import com.epam.hnyp.task1.subtask1.MusicInstrument;
import com.epam.hnyp.task1.subtask1.Sax;
import com.epam.hnyp.task1.subtask1.Trumpet;
import com.epam.hnyp.task1.subtask1.Violin;

public class GoodsListTest {

	private GoodsList<MusicInstrument> container;
	
	@Before
	public void before(){
		container = new GoodsList<>();
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
	public void testClear(){
		container.clear();
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());
		assertEquals(0, container.size());
	}

	@Test
	public void testToArray() {
		Object[] arr = container.toArray();
		assertEquals(9, arr.length);
		assertEquals(new Violin("aaa",2000, 4), arr[8]);
	}
	
	@Test
	public void testToArrayTNotEnoughSize() {
		MusicInstrument[] mas = new MusicInstrument[0];
		MusicInstrument[] mas_ret = container.toArray(mas);
		
		assertEquals(mas_ret.length, container.size());
		assertNotSame(mas, mas_ret);
	}
	
	@Test
	public void testToArrayTArrayEnoughSize() {
		MusicInstrument[] mas = new MusicInstrument[container.size()];
		MusicInstrument[] mas_ret = container.toArray(mas);
		
		mas_ret = container.toArray(mas);
		
		assertEquals(mas_ret.length, container.size());
		assertSame(mas, mas_ret);
	}

	@Test
	public void testAddE() {
		MusicInstrument m = new Sax();
		int sz = container.size();
		container.add(m);
		assertEquals(sz + 1, container.size());
	}
	
	@Test
	public void testAddEAddedToEnd() {
		MusicInstrument m = new Sax();
		container.add(m);
		assertEquals(m, container.get(container.size() - 1));
	}
	
	@Test
	public void testAddEIncreaseCapacity() {
		MusicInstrument m = new Sax();
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());
		container.add(m);
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());		
		container.add(m);
		assertEquals(GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH, container.capacity());
	}

	@Test
	public void testRemoveObject() {
		assertTrue(container.remove(new Violin()));
	}
	
	@Test
	public void testRemoveObjectFirstOccurence() {
		MusicInstrument m1 = new Violin();
		container.remove(m1);
		assertTrue(container.indexOf(m1) > 0);
	}
	
	@Test
	public void testRemoveObjectNotExist() {
		assertFalse(container.remove(new Sax("qwerty", 2002, "bronze")));
	}
	
	@Test
	public void testRemoveInt() {
		int sz = container.size();
		container.remove(0);
		assertEquals(sz - 1, container.size());
	}
	
	@Test
	public void testRemoveIntShiftElements() {
		MusicInstrument m1 = container.get(1);
		MusicInstrument m2 = container.get(container.size() - 1);
		container.remove(0);
		assertEquals(m1, container.get(0));
		assertEquals(m2, container.get(container.size() - 1));
	}
	
	@Test
	public void testRemoveIntReturn() {
		MusicInstrument m1 = container.get(0);
		assertEquals(m1, container.remove(0));
	}
	
	@Test
	public void testRemoveIntIndexOutOfBounds() {
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
	public void testRemoveIntCapacityNarrow() {
		MusicInstrument m = new Sax();
		container.add(m);
		container.add(m);
		assertEquals(GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH, container.capacity());
		container.remove(0);
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());
	}

	@Test
	public void testAddAllCollectionOfQextendsE() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		Guitar g1 = new Guitar("esp", 2003, 7, "black", StringType.METAL);
		Guitar g2 = new Guitar("ltd", 2000, 7, "black", StringType.METAL);
		Violin g3 = new Violin("123", 2009, 4);
		c.add(g1);
		c.add(g2);
		c.add(g3);
		
		int sz = container.size();
		container.addAll(c);
		assertEquals(sz + 3, container.size());
		assertEquals(g1, container.get(sz));
		assertEquals(g3, container.get(container.size() - 1));
	}
	
	@Test
	public void testAddAllCollectionOfQextendsEExtendStorage() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		Guitar g1 = new Guitar("esp", 2003, 7, "black", StringType.METAL);
		Guitar g2 = new Guitar("ltd", 2000, 7, "black", StringType.METAL);
		Violin g3 = new Violin("123", 2009, 4);
		c.add(g1);
		c.add(g2);
		c.add(g3);
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());
		container.addAll(c);
		assertEquals(GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH, container.capacity());
	}
	
	@Test
	public void testAddAllAtIndex() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		Guitar g1 = new Guitar("esp", 2003, 7, "black", StringType.METAL);
		Guitar g2 = new Guitar("ltd", 2000, 7, "black", StringType.METAL);
		Violin g3 = new Violin("123", 2009, 4);
		c.add(g1);
		c.add(g2);
		c.add(g3);
		
		int sz = container.size();
		assertTrue(container.addAll(0, c));
		assertEquals(sz + c.size(), container.size());
		assertEquals(g1, container.get(0));
		assertEquals(g3, container.get(2));
	}
	
	@Test
	public void testAddAllAtIndexIndexOutOfBounds() {
		try {
			container.addAll(container.size() + 1, Collections.EMPTY_LIST);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			container.addAll(-1, Collections.EMPTY_LIST);
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}
	
	@Test
	public void testAddAllAtIndexEmpty() {
		assertFalse(container.addAll(0, Collections.EMPTY_LIST));
	}

	@Test
	public void testRemoveAll() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		MusicInstrument m1 = new Violin();
		MusicInstrument m2 = new Trumpet("aaa", 2002, "coper");
		MusicInstrument m3 = new Trumpet("aaa", 2005, "coper");
		c.add(m1);
		c.add(m2);
		c.add(m3);
		
		assertTrue(container.removeAll(c));

		assertFalse(container.contains(m1));
		assertFalse(container.contains(m2));
		assertFalse(container.contains(m3));
	}
	
	@Test
	public void testRemoveAllNotContains() {
		GoodsList<MusicInstrument> c1 = new GoodsList<>();
		c1.add(new MusicInstrument("111", 1960));
		c1.add(new MusicInstrument("111", 1961));
		
		assertFalse(container.removeAll(c1));
	}
	
	@Test
	public void testRemoveAllEmpty() {
		assertFalse(container.removeAll(Collections.EMPTY_LIST));
	}
	
	@Test
	public void testContainsAll() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		MusicInstrument m1 = new Violin();
		MusicInstrument m2 = new Trumpet("aaa", 2002, "coper");
		MusicInstrument m3 = new Trumpet("aaa", 2005, "coper");
		c.add(m1);
		c.add(m2);
		c.add(m3);
		
		assertTrue(container.containsAll(c));
	}
	
	@Test
	public void testContainsAllFalse() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		c.add(new Trumpet("aaa", 2002, "coper"));
		c.add(new Guitar("epiphone", 2009, 6, "brown", StringType.METAL));
		
		assertFalse(container.containsAll(c));
	}
	
	@Test
	public void testContainsAllEmpty() {
		assertFalse(container.containsAll(Collections.EMPTY_LIST));
	}

	@Test
	public void testRetainAll() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		MusicInstrument m1 = new Violin();
		MusicInstrument m2 = new Trumpet("aaa", 2002, "coper");
		MusicInstrument m3 = new Trumpet("aaa", 2005, "coper");
		c.add(m1);
		c.add(m2);
		c.add(m3);
		
		container.add(new Trumpet("aaa1", 2005, "coper"));
		container.add(new Trumpet("aaa2", 2005, "coper"));
		container.add(new Trumpet("aaa3", 2005, "coper"));
		container.add(new Trumpet("aaa4", 2005, "coper"));
		container.add(new Trumpet("aaa5", 2005, "coper"));
		
		assertTrue(container.retainAll(c));
		assertEquals(4, container.size());
	}
	
	@Test
	public void testRetainAllNarrowStorage() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		MusicInstrument m1 = new Violin();
		MusicInstrument m2 = new Trumpet("aaa", 2002, "coper");
		MusicInstrument m3 = new Trumpet("aaa", 2005, "coper");
		c.add(m1);
		c.add(m2);
		c.add(m3);
		
		container.add(new Trumpet("aaa1", 2005, "coper"));
		container.add(new Trumpet("aaa2", 2005, "coper"));
		container.add(new Trumpet("aaa3", 2005, "coper"));
		container.add(new Trumpet("aaa4", 2005, "coper"));
		container.add(new Trumpet("aaa5", 2005, "coper"));
		
		assertTrue(container.retainAll(c));
		assertEquals(GoodsList.DEFAULT_LENGTH, container.capacity());
	}
	
	@Test
	public void testRetainAllEmpty() {
		assertFalse(container.retainAll(Collections.EMPTY_LIST));
	}
	
	@Test
	public void testRetainAllNotContainsAll() {
		GoodsList<MusicInstrument> c = new GoodsList<>();
		c.add(new Trumpet("aaa1", 2005, "coper"));
		c.add(new Trumpet("aaa2", 2005, "coper"));
		c.add(new Trumpet("aaa3", 2005, "coper"));
		
		assertTrue(container.retainAll(c));
		assertTrue(container.isEmpty());
	}
	

	@Test
	public void testGet() {
		assertEquals(new MusicInstrument(), container.get(0));
	}
	
	@Test
	public void testGetIndexOutOfBounds() {
		try {
			container.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			container.get(9);
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}
	
	@Test
	public void testSet() {
		MusicInstrument m = new Guitar();
		MusicInstrument m_prev = container.set(0, m);
		
		assertEquals(new MusicInstrument(), m_prev);
		assertEquals(m, container.get(0));
	}
	
	@Test
	public void testSetIndexOutOfBounds() {
		try {
			container.set(-1, new MusicInstrument());
			fail();
		} catch (IndexOutOfBoundsException e) {	}
		try {
			container.set(container.size(), new MusicInstrument());
			fail();
		} catch (IndexOutOfBoundsException e) {	}
	}

	@Test
	public void testAddIntE() {
		int sz = container.size();
		container.add(0, new Guitar());
		assertEquals(sz + 1, container.size());
		assertEquals(new Guitar(), container.get(0));
	}
	
	@Test
	public void testAddIntEElementsShifted() {
		int sz = container.size();
		
		MusicInstrument m1 = container.get(0);
		MusicInstrument m2 = container.get(sz - 1);
		
		container.add(0, new Guitar());
		assertEquals(m1, container.get(1));
		assertEquals(m2, container.get(sz));
	}
	
	@Test
	public void testAddIntEIndexOutOfBounds() {
		try {
			container.add(-1 ,new MusicInstrument());
			fail();
		} catch (IndexOutOfBoundsException e) {}
		try {
			container.add(container.size() + 1 ,new MusicInstrument());
			fail();
		} catch (IndexOutOfBoundsException e) {}
	}

	@Test
	public void testIndexOf() {
		assertEquals(2, container.indexOf(new Violin()));
	}

	@Test
	public void testLastIndexOf() {
		assertEquals(5, container.lastIndexOf(new Violin()));
	}
	
	@Test
	public void testSubList() {
		List<MusicInstrument> sub = container.subList(6, 9);
		assertEquals(3, sub.size());
		assertEquals(new Guitar(), sub.get(0));
		assertEquals(new Violin("aaa",2000, 4), sub.get(2));
	}
	
	@Test
	public void testSubListEmpty() {
		assertEquals(0, container.subList(1, 1).size());
	}
	
	@Test
	public void testSubListIndexOutOfBounds() {
		try {
			container.subList(2, 1);
			fail();
		} catch (IndexOutOfBoundsException e) {	}
	}
	
	@Test
	public void testSubListEmptyResult() {
		List<MusicInstrument> res = container.subList(0, 0);
		assertTrue(res.isEmpty());
	}

}
