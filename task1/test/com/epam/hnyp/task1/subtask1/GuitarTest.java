package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuitarTest {

	@Test
	public void testEquals() {
		Guitar g1 = new Guitar();
		Guitar g2 = new Guitar();
		assertTrue(g1.equals(g2));
	}
	
	@Test
	public void testEqualsFalse() {
		Guitar g1 = new Guitar();
		Guitar g2 = new Guitar();
		g2.setColor("sunburst");
		assertFalse(g1.equals(g2));
	}
}
