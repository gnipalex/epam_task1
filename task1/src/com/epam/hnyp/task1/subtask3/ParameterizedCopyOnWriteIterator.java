package com.epam.hnyp.task1.subtask3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.epam.hnyp.task1.subtask2.Condition;

public class ParameterizedCopyOnWriteIterator<E> implements Iterator<E> {
	private ListIteratorBridge<E> bridge;
	private Condition<E> condition;

	private int curentIndex;

	public ParameterizedCopyOnWriteIterator(ListIteratorBridge<E> bridge, Condition<E> condition) {
		this.bridge = bridge;
		this.condition = condition;
	}

	@Override
	public boolean hasNext() {
		int i = curentIndex;
		while (i < bridge.getList().size()) {
			E e = bridge.getList().get(i);
			if (condition != null) {
				if (!condition.satisfy(e)) {
					i++;
					continue;
				}
			}
			return true;
		}
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
