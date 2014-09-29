package com.epam.hnyp.task6.subtask1;

import java.util.ArrayList;
import java.util.List;

public class PrimeThreadRunner {
	private int rangeStart;
	private int rangeEnd;
	private int thrCount;
	private boolean useSeparateLists;
	private List<Integer> output;

	protected List<Runnable> tasks = new ArrayList<>();

	public PrimeThreadRunner(int rangeStart, int rangeEnd, int thrCount,
			List<Integer> output, boolean useSeparateLists) {
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
		createTasksSimpleAlgorithm();
		executeAllTasks();
	}

	/**
	 * Algorithm : if we have 5 tasks then ranges will divide into length = 5x +
	 * 4x + 3x + 2x + x, first task uses 5x length and etc.
	 */
	private void createTasksXAlgorithm() {
		int len = rangeEnd - rangeStart;

		double x = (2d * len) / ((1 + thrCount) * thrCount);

		for (int i = thrCount, end = rangeStart; i >= 1 && end < rangeEnd; i--) {
			double lenthToThread = i * x;
			if (lenthToThread > 1) {
				int a = (int) Math.ceil(lenthToThread);
				if (end + a >= rangeEnd) {
					createNewTask(end, rangeEnd, output, useSeparateLists);
					end = rangeEnd;
				} else {
					createNewTask(end, end + a, output, useSeparateLists);
					end += a;
				}
			} else {
				createNewTask(end, rangeEnd, output, useSeparateLists);
				end = rangeEnd;
			}
		}
	}

	private void createTasksSimpleAlgorithm() {
		int len = rangeEnd - rangeStart;
		int chunkSz = len / thrCount;

		if (chunkSz > 0) {
			for (int i = 0; i < thrCount; i++) {
				int start = rangeStart + chunkSz * i;
				int end = rangeStart + chunkSz * (i + 1);
				if (i == thrCount - 1) {
					end = rangeEnd;
				}
				createNewTask(start, end, output, useSeparateLists);
			}
		} else {
			for (int i = rangeStart; i < rangeEnd; i++) {
				createNewTask(i, i + 1, output, useSeparateLists);
			}
		}
	}

	protected void createNewTask(int startItem, int endItem,
			List<Integer> resultPrimesList, boolean useSeparateLists) {
		tasks.add(new PrimeFinder(startItem, endItem, resultPrimesList,
				useSeparateLists));
	}

	protected void executeAllTasks() {
		Thread[] threads = new Thread[tasks.size()];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(tasks.get(i));
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
