package com.epam.hnyp.task2.subtask4;

import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.management.RuntimeErrorException;

import com.epam.hnyp.task2.subtask3.ParameterizedIterator;


/**
 * Class represents implementation of List interface, consist of two parts : unmodifiable and modifiable. All 
 * modifying operations possible only with modifiable part. Unmodifiable part goes first and then the modifiable part. 
 * @author Oleksandr_Hnyp
 *
 * @param <E> type of containing elements
 */
public class ListHalfModifiable<E> implements List<E> {
	private List<E> unmodPart;
	private List<E> modPart;
	
	
	public ListHalfModifiable(List<E> unmodifiablePart, List<E> modifiablePart) {
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
		//just using already implemented iterator
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
				System.arraycopy(modPart.toArray(), 0, arr, unmodPart.size(), modPart.size());
			} else {
				System.arraycopy(modPart.toArray(), 0, arr, 0, modPart.size());
			}
		}
		return arr;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		T[] part1 = (T[])Array.newInstance(a.getClass(), unmodPart.size());
		part1 = unmodPart.toArray(part1);
		T[] part2 = (T[])Array.newInstance(a.getClass(), modPart.size());
		part2 = modPart.toArray(part2);
		
		T[] result = (T[])Array.newInstance(a.getClass(), part1.length + part2.length);
		
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
		//add to modPart only
		return modPart.add(e);
	}
	
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
	
	private static class UnmodifiedCollectionException extends RuntimeException { }
 	
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!(unmodPart.contains(o) || modPart.contains(o))){
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
		if (unmodPart.isEmpty()) {
			if (index == modPart.size())
		}
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		Object[] ob = new Object[0];
		System.out.println(ob + " size = " + ob.length);
	}
	
	
}
