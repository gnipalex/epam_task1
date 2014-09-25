package com.epam.hnyp.task6.subtask1;

public class TestIntervals {
	public static void main(String[] args) {
		int rangeStart = 1;
		int rangeEnd = 500;
		int thrCount = 100;
		
		int len = rangeEnd - rangeStart;
		
		double x = (2d * len) / ((1 + thrCount) * thrCount);
		
		for (int i = thrCount, end = rangeStart; i >= 1 && end < rangeEnd; i--) {
			double lenthToThread = i*x;
			if (lenthToThread > 1) {
				int a = (int)Math.ceil(lenthToThread);
				if (end + a >= rangeEnd) {
					System.out.println(i + " > 1 | " + end + " " + rangeEnd);
					end = rangeEnd;
				} else {
					System.out.println(i + " > 1 | " + end + " " + (end + a));
					end += a;
				}
			} else {
				System.out.println(i + " < 1 | " + end + " " + rangeEnd);
				end = rangeEnd;
			}
		}
	}

	/**
	 * 
	 * @param startIndex inclusive
	 * @param lastIndex exclusive
	 * @param intervalsCount count of big intervals
	 * @return
	 */
	public static double evalSmalIntervalSize(int startIndex, int lastIndex,
			int intervalsCount) {
		if (startIndex > lastIndex || intervalsCount < 1) {
			throw new IllegalArgumentException();
		}
		double len = lastIndex - startIndex;
		return 2 * len / ((1 + intervalsCount) * intervalsCount);
	}
}
