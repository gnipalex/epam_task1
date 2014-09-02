package com.epam.hnyp.task2.subtask2;

public class StringKeyWraperCodes extends StringKeyWraper {
	
	public StringKeyWraperCodes(String key) {
		super(key);
	}
	
	@Override
	public int hashCode() {
		String key = getKey();
		int hash = 0;
		for (int i=0; i<4 && i<key.length(); i++) {
			hash += key.charAt(i);
		}
		return hash;
	}
}
