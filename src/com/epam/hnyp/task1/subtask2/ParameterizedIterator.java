package com.epam.hnyp.task1.subtask2;

import java.util.Iterator;
import java.util.List;

public class ParameterizedIterator<E> implements Iterator<E> {
	private List<E> list;
	private Condition<E> condition;

	private int curentIndex;
	private E curentElement;

	public ParameterizedIterator(List<E> list, Condition<E> condition) {
		this.list = list;
		this.condition = condition;
	}

	@Override
	public boolean hasNext() {
		while (curentIndex < list.size()) {
			// if condition is not specified just take next element
			E e = list.get(curentIndex++);
			if (condition != null) {
				if (!condition.satisfy(e)) {
					continue;
				}
			}
			curentElement = e;
			return true;
		}
		return false;
	}

	@Override
	public E next() {
		return curentElement;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
