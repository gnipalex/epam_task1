package com.epam.hnyp.task6.subtask1;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents finder to prime numbers. Uses synchronized access to output source.
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
	 * @param startItem start of range (inclusive)
	 * @param endItem end of range (exclusive)
	 * @param resultPrimesList output result List
	 */
	public PrimeFinder(int startItem, int endItem, List<Integer> resultPrimesList) {
		if (startItem > endItem) {
			throw new IllegalArgumentException();
		}
		this.startItem = startItem;
		this.endItem = endItem;
		this.resultPrimesList = resultPrimesList;
	}
	
	/**
	 * 
	 * @param startItem start of range (inclusive)
	 * @param endItem end of range (exclusive)
	 * @param resultPrimesList output result List
	 * @param useSeparateLists indicates whether PrimeFinder should use separate List
	 * to contain found prime values before flush it to output
	 */
	public PrimeFinder(int startItem, int endItem, List<Integer> resultPrimesList, boolean useSeparateLists) {
		this(startItem, endItem, resultPrimesList);
		this.useSeparateLists = useSeparateLists;
	}
	
	
	
	@Override
	public void run() {
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
	 * @param output List to append found values
	 */
	public void findPrimes(List<Integer> output) {
		outer: for (int i=startItem; i<endItem; i++) {
			if (i<2) {
				continue outer;
			}
			if (i == 2 || i == 3) {
				addToPrimes(i, output);
				continue outer;
			}
			inner: for (int delim = 2, middle = i/2; delim <= middle; delim++) {
				if (i%delim == 0) {
					continue outer;
				}
			}
			addToPrimes(i, output);
		}
	}
	
	private void addToPrimes(int val, List<Integer> output) {
		if (useSeparateLists) {
			output.add(val);
		} else {
			synchronized (output) {
				output.add(val);
			}
		}
	}

}
