package com.epam.hnyp.task2.subtask3;

import java.util.List;

public class ListBridge<E> {
	private List<E> data;

	public ListBridge(List<E> data) {
		this.data = data;
	}
	
	public List<E> getData() {
		return data;
	}
	
	public void setData(List<E> data) {
		this.data = data;
	}
}
