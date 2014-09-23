package com.epam.hnyp.task1.subtask1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.hnyp.task1.subtask1.Guitar.StringType;

public class MusicInstrumentTest {
	@Test
	public void testEqualsSymetry() {
		MusicInstrument m1 = new MusicInstrument();
		MusicInstrument m2 = new MusicInstrument();
		assertTrue(m1.equals(m2) && m2.equals(m1));
	}
	
	@Test
	public void testEqualsReflection() {
		MusicInstrument m1 = new MusicInstrument();
		assertTrue(m1.equals(m1));
	}
	
	@Test
	public void testEqualsTransition() {
		MusicInstrument m1 = new MusicInstrument();
		MusicInstrument m2 = new MusicInstrument();
		MusicInstrument m3 = new MusicInstrument();
		assertTrue(m1.equals(m2) && m2.equals(m3) && m1.equals(m3));
	}
	
	@Test
	public void testEqualsNull() {
		MusicInstrument m1 = new MusicInstrument();
		assertFalse(m1.equals(null));
	}
	
	@Test
	public void testEqualsConstancy() {
		MusicInstrument m1 = new MusicInstrument();
		MusicInstrument m2 = new MusicInstrument();
		m1.setYear(m1.getYear() - 1);
		assertFalse(m1.equals(m2));
		m1.setYear(m1.getYear() + 1);
		assertTrue(m1.equals(m2));
	}
}
