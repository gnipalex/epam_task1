package com.epam.hnyp.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.hnyp.task6.subtask1.PrimeThreadRunner;
import com.epam.hnyp.task6.subtask2.PrimeExecutorRunner;

public class ConsoleFindPrimes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int start = 0;
		int end = 0;
		int thrCount = 0;
		
		System.out.print("Enter left border : ");
		try {
			start = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("##wrong input##");
			return;
		}
		
		System.out.print("Enter right border : ");
		try {
			end = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("##wrong input##");
			return;
		}
		if (end - start <= 0) {
			System.out.println("##left border must be less than rigth border##");
			return;
		}
		
		System.out.print("Tasks count : ");
		try {
			thrCount = Integer.parseInt(sc.nextLine());
			if (thrCount <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			System.out.println("##wrong input##");
			return;
		}

		//threads, separate lists
		List<Integer> output = new ArrayList<>();
		PrimeThreadRunner runnerThreadSep = new PrimeThreadRunner(start, end, thrCount, output, true);
		long timeStart = System.currentTimeMillis();
		runnerThreadSep.run();
		long timeFin = System.currentTimeMillis();
		System.out.println("Mode threads, separate lists");
		System.out.println("elapsed ms --> " + (timeFin - timeStart));
		System.out.println("prime count --> " + output.size());
		
		//threads, single list
		output.clear();
		PrimeThreadRunner runnerThread = new PrimeThreadRunner(start, end, thrCount, output, false);
		timeStart = System.currentTimeMillis();
		runnerThread.run();
		timeFin = System.currentTimeMillis();
		System.out.println("Mode threads, single list");
		System.out.println("elapsed ms --> " + (timeFin - timeStart));
		System.out.println("prime count --> " + output.size());
		
		//executor
		output.clear();
		PrimeThreadRunner runnerExecutor = new PrimeExecutorRunner(start, end, thrCount, output, false);
		timeStart = System.currentTimeMillis();
		runnerExecutor.run();
		timeFin = System.currentTimeMillis();
		System.out.println("Mode executor, single list");
		System.out.println("elapsed ms --> " + (timeFin - timeStart));
		System.out.println("prime count --> " + output.size());
	}
}
