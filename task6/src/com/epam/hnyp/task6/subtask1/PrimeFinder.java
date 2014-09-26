package com.epam.hnyp.task6.subtask1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents finder to prime numbers. Uses synchronized access to output
 * source.
 * 
 * @author Oleksandr_Hnyp
 * 
 */
public class PrimeFinder implements Runnable {

	private List<Integer> resultPrimesList;
	private int startItem;
	private int endItem;
	private boolean useSeparateLists;

	/**
	 * 
	 * @param startItem
	 *            start of range (inclusive)
	 * @param endItem
	 *            end of range (exclusive)
	 * @param resultPrimesList
	 *            output result List
	 */
	public PrimeFinder(int startItem, int endItem,
			List<Integer> resultPrimesList) {
		if (startItem > endItem) {
			throw new IllegalArgumentException();
		}
		this.startItem = startItem;
		this.endItem = endItem;
		this.resultPrimesList = resultPrimesList;
	}

	/**
	 * 
	 * @param startItem
	 *            start of range (inclusive)
	 * @param endItem
	 *            end of range (exclusive)
	 * @param resultPrimesList
	 *            output result List
	 * @param useSeparateLists
	 *            indicates whether PrimeFinder should use separate List to
	 *            contain found prime values before flush it to output
	 */
	public PrimeFinder(int startItem, int endItem,
			List<Integer> resultPrimesList, boolean useSeparateLists) {
		this(startItem, endItem, resultPrimesList);
		this.useSeparateLists = useSeparateLists;
	}

	@Override
	public void run() {
//		System.err.println(Thread.currentThread().getName() + " : " + startItem
//				+ " - " + endItem);
		List<Integer> outputPrimes = null;
		if (useSeparateLists) {
			outputPrimes = new ArrayList<>();
		} else {
			outputPrimes = resultPrimesList;
		}
		findPrimes(outputPrimes);
		if (useSeparateLists) {
			synchronized (resultPrimesList) {
				resultPrimesList.addAll(outputPrimes);
			}
		}
	}

	/**
	 * Finds all primes in range
	 * 
	 * @param output
	 *            List to append found values
	 */
	public void findPrimes(List<Integer> output) {
		for (int i = startItem; i < endItem; i++) {
			if (isPrime(i)) {
				addToPrimes(i, output);
			}
		}
	}

	private final boolean isPrime(int val) {
		if (val == 2 || val == 3) {
			return true;
		}
		if (val < 2 || val % 2 == 0) {
			return false;
		}
		for (int delim = 3; delim*delim <= val; delim += 2) {
			if (val % delim == 0) {
				return false;
			}
		}
		return true;
	}

	private final void addToPrimes(int val, List<Integer> output) {
		if (useSeparateLists) {
			output.add(val);
		} else {
			synchronized (output) {
				output.add(val);
			}
		}
	}

}
