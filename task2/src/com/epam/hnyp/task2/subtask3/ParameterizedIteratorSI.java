package com.epam.hnyp.task2.subtask3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ParameterizedIteratorSI<E> implements Iterator<E> {
	private ListIteratorBridge<E> bridge;
	private Condition<E> condition;

	private int curentIndex;
	//private E curentElement;

	public ParameterizedIteratorSI(ListIteratorBridge<E> bridge, Condition<E> condition) {
		this.bridge = bridge;
		this.condition = condition;
	}

	@Override
	public boolean hasNext() {
		int ci = curentIndex;
		while (ci < bridge.getList().size()) {
			// if condition is not specified just take next element
			E e = bridge.getList().get(ci);
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
		while (curentIndex >= 0 && curentIndex < bridge.getList().size()) {
			E e = bridge.getList().get(curentIndex++);
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

	ListIteratorBridge<E> getBridge() {
		return bridge;
	}
	
}
