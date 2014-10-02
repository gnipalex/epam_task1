package com.epam.hnyp.task2.subtask2;

public abstract class StringKeyWraper {
	private String key;
	
	public StringKeyWraper(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof StringKeyWraper)) {
			return false;
		}
		String keyO = ((StringKeyWraper)obj).key;
		return key == keyO ? true : key.equals(keyO);
	}
}
