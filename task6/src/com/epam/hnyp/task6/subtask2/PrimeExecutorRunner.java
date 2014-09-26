package com.epam.hnyp.task6.subtask2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.epam.hnyp.task6.subtask1.PrimeFinder;
import com.epam.hnyp.task6.subtask1.PrimeThreadRunner;

public class PrimeExecutorRunner extends PrimeThreadRunner {
	private ExecutorService pool;
	private List<Callable<Object>> callables = new ArrayList<>();
	
	public PrimeExecutorRunner(int rangeStart, int rangeEnd, int thrCount,
			List<Integer> output, boolean useSeparateLists) {
		super(rangeStart, rangeEnd, thrCount, output, useSeparateLists);
		this.pool = Executors.newFixedThreadPool(thrCount);
	}
	
	@Override
	protected void createNewTask(int startItem, int endItem,
			List<Integer> resultPrimesList, boolean useSeparateLists) {
		callables.add(Executors.callable(new PrimeFinder(startItem, endItem, resultPrimesList, useSeparateLists)));
	}
	
	@Override
	protected void executeAllTasks() {
		try {
			this.pool.invokeAll(callables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.pool.shutdown();
		try {
			this.pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
