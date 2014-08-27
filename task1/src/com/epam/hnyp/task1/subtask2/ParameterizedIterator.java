package com.epam.hnyp.task1.subtask2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ParameterizedIterator<E> implements Iterator<E> {
	private List<E> list;
	private Condition<E> condition;

	private int curentIndex;
	//private E curentElement;

	public ParameterizedIterator(List<E> list, Condition<E> condition) {
		this.list = list;
		this.condition = condition;
	}

	@Override
	public boolean hasNext() {
		int ci = curentIndex;
		while (ci < list.size()) {
			// if condition is not specified just take next element
			E e = list.get(ci);
			if (condition != null) {
				if (!condition.satisfy(e)) {
					ci++;
					continue;
				}
			}
			//curentElement = e;
			//we found element and now saving its index
			curentIndex = ci++;
			return true;
		}
		//curentElement = null;
		curentIndex = -1;
		return false;
	}

	@Override
	public E next() {
		while (curentIndex >= 0 && curentIndex < list.size()) {
			E e = list.get(curentIndex++);
			if (condition != null) {
				if (!condition.satisfy(e)) {
					continue;
				}
			}
			return e;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
