package com.epam.hnyp.task1.subtask4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.epam.hnyp.task1.subtask2.ParameterizedIterator;

/**
 * Class represents implementation of List interface, consist of two parts :
 * unmodifiable and modifiable. All modifying operations possible only with
 * modifiable part. Unmodifiable part goes first and then the modifiable part.
 * 
 * @author Oleksandr_Hnyp
 * 
 * @param <E>
 *            type of containing elements
 */
public class HalfModifiableList<E> implements List<E> {
	private List<E> unmodPart;
	private List<E> modPart;

	/**
	 * Creates List with two parts : unmodifiable and modifiable
	 * 
	 * @param unmodifiablePart
	 * @param modifiablePart
	 */
	public HalfModifiableList(List<E> unmodifiablePart, List<E> modifiablePart) {
		this.unmodPart = unmodifiablePart;
		this.modPart = modifiablePart;
	}

	@Override
	public int size() {
		return unmodPart.size() + modPart.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return unmodPart.contains(o) ? true : modPart.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		// just using already implemented iterator
		return new ParameterizedIterator<>(this, null);
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size()];
		if (unmodPart.size() > 0) {
			System.arraycopy(unmodPart.toArray(), 0, arr, 0, unmodPart.size());
		}
		if (modPart.size() > 0) {
			if (unmodPart.size() > 0) {
				System.arraycopy(modPart.toArray(), 0, arr, unmodPart.size(),
						modPart.size());
			} else {
				System.arraycopy(modPart.toArray(), 0, arr, 0, modPart.size());
			}
		}
		return arr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		Class<?> elem_type = a.getClass().getComponentType();// !!!!

		T[] part1 = (T[]) Array.newInstance(elem_type, unmodPart.size());
		part1 = unmodPart.toArray(part1);
		T[] part2 = (T[]) Array.newInstance(elem_type, modPart.size());
		part2 = modPart.toArray(part2);

		int need_sz = part1.length + part2.length;
		T[] result = a;
		if (a.length < need_sz) {
			result = (T[]) Array.newInstance(elem_type, need_sz);
		}

		if (part1.length > 0) {
			System.arraycopy(part1, 0, result, 0, part1.length);
		}
		if (part2.length > 0) {
			if (part1.length > 0) {
				System.arraycopy(part2, 0, result, part1.length, part2.length);
			} else {
				System.arraycopy(part2, 0, result, 0, part2.length);
			}
		}

		return result;
	}

	@Override
	public boolean add(E e) {
		// add to modPart only
		return modPart.add(e);
	}

	/**
	 * @throws UnmodifiedCollectionException if element is contained in unmodifiable part
	 */
	@Override
	public boolean remove(Object o) {
		boolean b1 = unmodPart.contains(o);
		boolean b2 = modPart.contains(o);
		if (b1 & b2 || b2) {
			return modPart.remove(o);
		} else if (b1) {
			throw new UnmodifiedCollectionException();
		}
		return false;
	}

	public static class UnmodifiedCollectionException extends
			IllegalArgumentException {
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (c.isEmpty()) {
			return false;
		}
		for (Object o : c) {
			if (!(unmodPart.contains(o) || modPart.contains(o))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return modPart.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		int ind_n = index - unmodPart.size();
		if (ind_n < 0) {
			throw new UnmodifiedCollectionException();
		}
		return modPart.addAll(ind_n, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object o : c) {
			if (unmodPart.contains(o)) {
				throw new UnmodifiedCollectionException();
			}
		}
		return modPart.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!unmodPart.containsAll(c) || c.size() < unmodPart.size()) {
			throw new UnmodifiedCollectionException();
		}
		return modPart.retainAll(c);
	}

	@Override
	public void clear() {
		if (!unmodPart.isEmpty()) {
			throw new UnmodifiedCollectionException();
		}
		modPart.clear();
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (unmodPart.isEmpty()) {
			return modPart.get(index);
		}
		if (index < unmodPart.size()) {
			return unmodPart.get(index);
		}
		return modPart.get(index - unmodPart.size());
	}

	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (unmodPart.isEmpty()) {
			return modPart.set(index, element);
		}
		if (index < unmodPart.size()) {
			throw new UnmodifiedCollectionException();
		}
		return modPart.set(index - unmodPart.size(), element);
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		int ind_n = index - unmodPart.size();
		if (ind_n < 0) {
			throw new UnmodifiedCollectionException();
		}
		modPart.add(ind_n, element);
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		int ind_n = index - unmodPart.size();
		if (ind_n < 0) {
			throw new UnmodifiedCollectionException();
		}
		return modPart.remove(ind_n);
	}

	@Override
	public int indexOf(Object o) {
		int index1 = unmodPart.indexOf(o);
		int index2 = modPart.indexOf(o);
		if (index1 >= 0) {
			return index1;
		}
		return index2 < 0 ? -1 : index2 + unmodPart.size();
	}

	@Override
	public int lastIndexOf(Object o) {
		int index1 = unmodPart.lastIndexOf(o);
		int index2 = modPart.lastIndexOf(o);
		if (index2 >= 0) {
			return index2 + unmodPart.size();
		}
		return index1 < 0 ? -1 : index1;
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
		if (toIndex - fromIndex < 0 || toIndex > size() || fromIndex < 0) {
			throw new IndexOutOfBoundsException();
		}		
		if (toIndex - fromIndex == 0) {
			return new ArrayList<>();
		}
		List<E> sublist = new ArrayList<>();
		for (int i = fromIndex; i < toIndex; i++) {
			sublist.add(get(i));
		}
		return sublist;
	}

	/**
	 * Retrieves index modifiable part starts at.
	 * 
	 * @return index
	 */
	public int modifyingStartAtIndex() {
		return unmodPart.isEmpty() ? 0 : unmodPart.size();
	}
}
