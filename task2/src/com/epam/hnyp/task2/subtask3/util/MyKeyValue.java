package com.epam.hnyp.task2.subtask3.util;

public class MyKeyValue<K, V> {
	private K key;
	private V value;
	
	public MyKeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
