package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import org.junit.Test;

public class SaxTest {

	@Test
	public void testEquals() {
		Sax s1 = new Sax();
		Sax s2 = new Sax();
		assertTrue(s1.equals(s2) && s2.equals(s1));
	}
	
	@Test
	public void testEqualsFalse() {
		Sax s1 = new Sax();
		Sax s2 = new Sax();
		s2.setMaterial("aaa");
		assertFalse(s1.equals(s2) && s2.equals(s1));
	}
	
	@Test
	public void testEqualsToAnotherClass() {
		MusicInstrument m1 = new MusicInstrument();
		Sax s1 = new Sax();
		assertFalse(m1.equals(s1));
	}

}
