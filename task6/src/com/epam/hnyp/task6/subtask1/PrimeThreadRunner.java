package com.epam.hnyp.task6.subtask1;

import java.util.List;

public class PrimeThreadRunner {
	private int rangeStart;
	private int rangeEnd;
	private int thrCount;
	private boolean useSeparateLists;
	private List<Integer> output;
	
	protected List<Runnable> tasks;
	
	public PrimeThreadRunner(int rangeStart, int rangeEnd, int thrCount, List<Integer> output,
			boolean useSeparateLists) {
		if (rangeStart > rangeEnd) {
			throw new IllegalArgumentException();
		}
		if (thrCount < 1) {
			throw new IllegalArgumentException();
		}
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
		this.thrCount = thrCount;
		this.output = output;
		this.useSeparateLists = useSeparateLists;
	}
	
	public void run() {
		createTasks();
		executeAllTasks();
	}
	
	private void createTasks() {
		int len = rangeEnd - rangeStart;
		double x = (2d * len) / ((1 + thrCount) * thrCount);
		
		for (int i = thrCount, end = rangeStart; i >= 1 && end < rangeEnd; i--) {
			double lenthToThread = i*x;
			if (lenthToThread > 1) {
				int a = (int)Math.ceil(lenthToThread);
				if (end + a >= rangeEnd) {
					tasks.add(new PrimeFinder(end, rangeEnd, output, useSeparateLists));
					end = rangeEnd;
				} else {
					tasks.add(new PrimeFinder(end, end + a, output, useSeparateLists));
					end += a;
				}
			} else {
				tasks.add(new PrimeFinder(end, rangeEnd, output, useSeparateLists));
				end = rangeEnd;
			}
		}
	}
	
	protected void createNewTask() {}
	
	protected void executeAllTasks(){
		
	}
	
	/**
	 * Evaluate coeficient x to equation
	 * len = x*intervalsCount + x*(intervalsCount - 1) + ... + x*1
	 * @param len length of data range
	 * @param intervalsCount count of big intervals
	 * @return
	 */
	public double evalSmalIntervalSize(int len, int lastIndex,
			int intervalsCount) {
		return 2d * len / ((1 + intervalsCount) * intervalsCount);
	}

	
	
}
