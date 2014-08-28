package com.epam.hnyp.task2.subtask3;

import java.util.Collection;
import java.util.Iterator;

public class GoodsContainerSI<E> extends GoodsContainer<E> {
	private ListIteratorBridge<E> bridge;
	private boolean hasIteratorsToCurBridge;

	public GoodsContainerSI() {
		initBridge();
	}

	public GoodsContainerSI(int capacity) {
		super(capacity);
		initBridge();
	}

	public GoodsContainerSI(int capacity, int extraLen) {
		super(capacity, extraLen);
		initBridge();
	}

	private void initBridge() {
		bridge = new ListIteratorBridge<>(this);
	}

	@SuppressWarnings("unchecked")
	private void leaveBridge() {
		if (hasIteratorsToCurBridge) {
			// all past iterators now will work with copy of this list
			bridge.setList((GoodsContainer<E>) this.clone());
			// new iterators must work with modified list, so we creating new
			// bridge
			// and 'forgeting' about old bridge
			initBridge();
			hasIteratorsToCurBridge = false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		// iterator works through bridge
		hasIteratorsToCurBridge = true;
		return new ParameterizedIteratorSI<>(bridge,
				this.getIteratorCondition());
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		leaveBridge();
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		leaveBridge();
		return super.addAll(index, c);
	}

	@Override
	public E remove(int index) {
		leaveBridge();
		return super.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		leaveBridge();
		return super.removeAll(c);
	}

	@Override
	public boolean remove(Object o) {
		leaveBridge();
		return super.remove(o);
	}

	@Override
	public E set(int index, E element) {
		leaveBridge();
		return super.set(index, element);
	}

	@Override
	public boolean add(E e) {
		leaveBridge();
		return super.add(e);
	}

	@Override
	public void add(int index, E element) {
		leaveBridge();
		super.add(index, element);
	}
	
	@Override
	public void clear() {
		leaveBridge();
		super.clear();
	}
	
	ListIteratorBridge<E> getBridge() {
		return bridge;
	}
}
