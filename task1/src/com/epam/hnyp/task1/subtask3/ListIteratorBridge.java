package com.epam.hnyp.task1.subtask3;

import java.util.List;

public class ListIteratorBridge<E> {
	private List<E> list;

	public ListIteratorBridge(List<E> list) {
		this.list = list;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
