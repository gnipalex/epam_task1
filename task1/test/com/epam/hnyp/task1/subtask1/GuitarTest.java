package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuitarTest {

	@Test
	public void testEqualsSymetry() {
		Guitar g1 = new Guitar();
		Guitar g2 = new Guitar();
		assertTrue(g1.equals(g2) && g2.equals(g1));
	}
	
	@Test
	public void testEqualsReflection() {
		Guitar g1 = new Guitar();
		assertTrue(g1.equals(g1));
	}
	
	@Test
	public void testEqualsTransition() {
		Guitar g1 = new Guitar();
		Guitar g2 = new Guitar();
		Guitar g3 = new Guitar();
		assertTrue(g1.equals(g2) && g2.equals(g3) && g1.equals(g3));
	}
	
	@Test
	public void testEqualsConstancy() {
		Guitar g1 = new Guitar();
		Guitar g2 = new Guitar();
		g1.setYear(g1.getYear() - 1);
		assertFalse(g1.equals(g2));
		g1.setYear(g1.getYear() + 1);
		assertTrue(g1.equals(g2));
	}
}
