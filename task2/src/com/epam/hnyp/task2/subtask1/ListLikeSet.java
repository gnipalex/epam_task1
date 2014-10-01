package com.epam.hnyp.task2.subtask1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents List which behaves like Set : cannot contain dublicate values.
 * @author Oleksandr_Hnyp
 *
 * @param <E>
 */
public class ListLikeSet<E> extends ArrayList<E> {
	
	@Override
	public boolean add(E e) {
		checkContains(e);
		return super.add(e);
	}
	
	private void checkContains(E e) {
		if (this.contains(e)) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void add(int index, E element) {
		checkContains(element);
		super.add(index, element);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size(), c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean changed = false;
		Set<? extends E> set = new HashSet<>(c);
		if (set.size() != c.size()) {
			throw new IllegalArgumentException();
		}
		for (E o : c) {
			if (this.contains(o)){
				throw new IllegalArgumentException();
			}
			this.add(index++, o);
			changed = true;
		}
		return changed;
	}
	
	@Override
	public E set(int index, E element) {
		if (this.contains(element) && !this.get(index).equals(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}
}
