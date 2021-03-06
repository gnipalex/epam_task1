package com.epam.hnyp.task1.subtask2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GoodsList<E> implements List<E>, Cloneable {

	public static final int DEFAULT_EXTRA_LENGTH = 10;
	public static final int DEFAULT_LENGTH = 10;

	private Object[] elements;
	private int size;
	private int initCapacity;
	private int extraLength;

	private Condition<E> iteratorCondition;

	public GoodsList() {
		extraLength = DEFAULT_EXTRA_LENGTH;
		elements = new Object[DEFAULT_LENGTH];
		initCapacity = DEFAULT_LENGTH;
	}

	public GoodsList(int capacity) {
		extraLength = DEFAULT_EXTRA_LENGTH;
		elements = new Object[capacity];
		this.initCapacity = capacity;
	}

	public GoodsList(int capacity, int extraLen) {
		extraLength = extraLen;
		elements = new Object[capacity];
		this.initCapacity = capacity;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new ParameterizedIterator<>(this, iteratorCondition);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(elements, size, a.getClass());
		}
		System.arraycopy(elements, 0, a, 0, size);
		return a;
	}

	private void extendStorage() {
		extendStorage(extraLength);
	}

	private void extendStorage(int extraLen) {
		Object[] storage = new Object[this.elements.length + extraLen];
		System.arraycopy(elements, 0, storage, 0, size);
		this.elements = storage;
	}

	private void narrowStorage() {
		narrowStorage(extraLength);
	}

	private void narrowStorage(int length) {
		Object[] storage = Arrays.copyOfRange(elements, 0, elements.length
				- length);
		this.elements = storage;
	}

	@Override
	public boolean add(E e) {
		if (size == elements.length) {
			extendStorage();
		}
		elements[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		try {
			remove(indexOf(o), true);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	@Override
	public E remove(int index) {
		return remove(index, true);
	}

	private void indexRangeCheck(int index, boolean includeSize) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (!includeSize && index == size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private E remove(int index, boolean narrow) {
		indexRangeCheck(index, false);
		
		E removedItem = (E) elements[index];
		if (index < size - 1) {
			for (int i = index; i < size - 1; i++) {
				elements[i] = elements[i + 1];
			}
		}
		elements[--size] = null;
		if (narrow && elements.length > size
				&& elements.length >= initCapacity + extraLength
				&& elements.length - size >= extraLength) {
			narrowStorage();
		}
		return removedItem;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.isEmpty()) {
			return false;
		}
		for (Object o : c) {
			if (indexOf(o) < 0) {
				return false;
			}
		}
		return true;
	}

	private void extendsStorageAddAll(int addLen) {
		if (elements.length - size < addLen) {
			int x = addLen / extraLength;
			int extLen = x > 0 ? x : 1 * extraLength;
			extLen += (addLen / extraLength > 0 && addLen % extraLength > 0) ? extLen
					: 0;
			extendStorage(extLen);
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (c.isEmpty()) {
			return false;
		}
		
		extendsStorageAddAll(c.size());
		
		for (E i : c) {
			elements[size++] = i;
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		indexRangeCheck(index, true);
		if (c.isEmpty()) {
			return false;
		}
		if (index == size) {
			return addAll(c);
		}
		int addLen = c.size();

		extendsStorageAddAll(addLen);
		
		Object[] ost = new Object[size - index];
		System.arraycopy(elements, index, ost, 0, size - index);
		int tmp_index = index;
		for (E i : c) {
			elements[tmp_index++] = i;
		}
		System.arraycopy(ost, 0, elements, tmp_index, ost.length);
		size += addLen;

		return true;
	}
	
	private void narrowRemoveAll(int deletedCount) {
		int len = deletedCount / extraLength * extraLength;
		if (len > 0) {
			if (elements.length - len >= initCapacity) {
				narrowStorage(len);
			} else {
				// size of array must stay not less than initCapacity
				int x = initCapacity + len - elements.length;
				narrowStorage(len - x);
			}
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (c.isEmpty()) {
			return false;
		}
		int deleted = 0;
		for (Object o : c) {
			int i = -1;
			do {
				i = indexOf(o);
				if (i >= 0) {
					remove(i, false);
					deleted++;
				}
			} while (i >= 0);
		}
		if (deleted == 0) {
			return false;
		}

		narrowRemoveAll(deleted);
		
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (c.isEmpty()) {
			return false;
		}
		int deleted = 0;
		for (int i = size - 1; i >= 0; i--) {
			if (!c.contains(elements[i])) {
				remove(i, false);
				deleted++;
			}
		}
		if (deleted == 0) {
			return false;
		}
		
		narrowRemoveAll(deleted);

		return true;
	}

	@Override
	public void clear() {
		size = 0;
		elements = new Object[initCapacity];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		indexRangeCheck(index, false);
		return (E) elements[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		indexRangeCheck(index, false);
		Object prev = elements[index];
		elements[index] = element;
		return (E) prev;
	}

	@Override
	public void add(int index, E element) {
		indexRangeCheck(index, true);
		if (index == size()) {
			add(element);
			return;
		}
		if (size == elements.length) {
			extendStorage();
		}
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		size++;
		elements[index] = element;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (elements[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		GoodsList<E> list = new GoodsList<>();
		if (toIndex - fromIndex == 0) {
			return list;
		}
		if (toIndex - fromIndex < 0 || toIndex > size() || fromIndex < 0) {
			throw new IndexOutOfBoundsException();
		}
		list.elements = Arrays.copyOfRange(elements, fromIndex, toIndex);
		list.size = toIndex - fromIndex;
		return list;
	}

	public int capacity() {
		return elements.length;
	}

	public Condition<E> getIteratorCondition() {
		return iteratorCondition;
	}

	public void setIteratorCondition(Condition<E> iteratorCondition) {
		this.iteratorCondition = iteratorCondition;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() {
		GoodsList<E> copy = null;
		try {
			copy = (GoodsList<E>)this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		copy.elements = Arrays.copyOf(elements, elements.length);
		copy.extraLength = extraLength;
		copy.initCapacity = initCapacity;
		copy.iteratorCondition = iteratorCondition;
		copy.size = size;
		
		return copy;
	}
}
