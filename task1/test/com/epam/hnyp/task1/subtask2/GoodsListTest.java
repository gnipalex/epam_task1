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
		
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH);
		assertTrue(container.size() == 0);
	}

	@Test
	public void testToArray() {
		Object[] arr = container.toArray();
		assertTrue(arr.length == 9);
		assertTrue(((MusicInstrument)arr[8]).equals(new Violin("aaa",2000, 4)));
	}
	
	@Test
	public void testToArrayTNotEnoughSize() {
		MusicInstrument[] mas = new MusicInstrument[0];
		MusicInstrument[] mas_ret = container.toArray(mas);
		
		assertTrue(mas_ret.length == container.size());
		assertTrue(mas != mas_ret);
	}
	
	@Test
	public void testToArrayTArrayEnoughSize() {
		MusicInstrument[] mas = new MusicInstrument[container.size()];
		MusicInstrument[] mas_ret = container.toArray(mas);
		
		mas_ret = container.toArray(mas);
		
		assertTrue(mas_ret.length == container.size());
		assertTrue(mas == mas_ret);
	}

	@Test
	public void testAddE() {
		MusicInstrument m = new Sax();
		int sz = container.size();
		container.add(m);
		assertTrue(container.size() == sz + 1);
	}
	
	@Test
	public void testAddEAddedToEnd() {
		MusicInstrument m = new Sax();
		container.add(m);
		assertTrue(container.get(container.size() - 1).equals(m));
	}
	
	@Test
	public void testAddEIncreaseCapacity() {
		MusicInstrument m = new Sax();
		assertTrue(container.size() == 9 && container.capacity() == GoodsList.DEFAULT_LENGTH);
		container.add(m);
		assertTrue(container.size() == 10 && container.capacity() == GoodsList.DEFAULT_LENGTH);
		container.add(m);
		assertTrue(container.size() == 11 && 
				container.capacity() == GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH);
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
		assertTrue(container.size() == sz - 1);
	}
	
	@Test
	public void testRemoveIntShiftElements() {
		MusicInstrument m1 = container.get(1);
		MusicInstrument m2 = container.get(container.size() - 1);
		container.remove(0);
		assertTrue(container.get(0).equals(m1));
		assertTrue(container.get(container.size() - 1).equals(m2));
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
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH);
		container.remove(0);
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH);
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
		assertTrue(container.size() == sz + 3);
		assertTrue(container.get(sz).equals(g1));
		assertTrue(container.get(container.size() - 1).equals(g3));
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
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH);
		container.addAll(c);
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH + GoodsList.DEFAULT_EXTRA_LENGTH);
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
		assertTrue(container.size() == sz + c.size());
		assertTrue(container.get(0).equals(g1));
		assertTrue(container.get(2).equals(g3));
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

		assertTrue(!container.contains(m1));
		assertTrue(!container.contains(m2));
		assertTrue(!container.contains(m3));
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
		
		assertTrue(container.size() == 4);
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
		
		assertTrue(container.capacity() == GoodsList.DEFAULT_LENGTH);
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
		assertTrue(container.get(0).equals(new MusicInstrument()));
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
		
		assertTrue(m_prev.equals(new MusicInstrument()));
		assertEquals(m, container.get(0));
	}
	
	@Test
	public void testSetIndexOutOfBounds() {
		try {
			container.set(-1, new MusicInstrument());
		} catch (IndexOutOfBoundsException e) {	}
		try {
			container.set(container.size(), new MusicInstrument());
		} catch (IndexOutOfBoundsException e) {	}
	}

	@Test
	public void testAddIntE() {
		int sz = container.size();
		container.add(0, new Guitar());
		assertTrue(container.size() == sz + 1);
		assertTrue(container.get(0).equals(new Guitar()));
	}
	
	@Test
	public void testAddIntEElementsShifted() {
		int sz = container.size();
		
		MusicInstrument m1 = container.get(0);
		MusicInstrument m2 = container.get(sz - 1);
		
		container.add(0, new Guitar());
		assertTrue(container.get(1).equals(m1));
		assertTrue(container.get(sz).equals(m2));
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
		assertTrue(container.indexOf(new Violin()) == 2);
	}

	@Test
	public void testLastIndexOf() {
		assertTrue(container.lastIndexOf(new Violin()) == 5);
	}
	
	@Test
	public void testSubList() {
		assertTrue(container.subList(1, 1).size() == 0);
		
		List<MusicInstrument> sub = container.subList(6, 9);
		assertTrue(sub.size() == 3);
		assertTrue(sub.get(0).equals(new Guitar()));
		assertTrue(sub.get(2).equals(new Violin("aaa",2000, 4)));
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
