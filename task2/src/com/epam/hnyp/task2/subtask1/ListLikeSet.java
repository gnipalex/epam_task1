package com.epam.hnyp.task2.subtask1;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents List which behaves like Set : cannot contain dublicate values.
 * @author Oleksandr_Hnyp
 *
 * @param <E>
 */
public class ListLikeSet<E> extends ArrayList<E> {
	
	@Override
	public boolean add(E e) {
		if (this.contains(e)) {
			return false;
		}
		return super.add(e);
	}
	
	/**
	 * @throws NonUniqueElementException if specified element already exists in list 
	 */
	@Override
	public void add(int index, E element) {
		if (this.contains(element)) {
			throw new NonUniqueElementException();
		}
		super.add(index, element);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size(), c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean changed = false;
		for (E o : c) {
			if (this.contains(o)){
				continue;
			}
			this.add(index++, o);
			changed = true;
		}
		return changed;
	}
	
	/**
	 * @throws NonUniqueElementException if specified element already exists in list
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element) && !this.get(index).equals(element)) {
			throw new NonUniqueElementException();
		}
		return super.set(index, element);
	}
	
	public static class NonUniqueElementException extends RuntimeException {}
}
