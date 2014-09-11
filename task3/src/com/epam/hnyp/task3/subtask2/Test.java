package com.epam.hnyp.task3.subtask2;

import java.io.File;

public class Test {
	public static void main(String[] args) throws Exception {
		File f = new File("aaa.txt");
		System.out.println("File exist? --> " + f.exists());
		System.out.println("Absolute path --> " + f.getAbsolutePath());
		System.out.println("Canonical path --> " + f.getCanonicalPath());
		System.out.println("Name --> " + f.getName());
		System.out.println("Parent --> " + f.getParent());
		System.out.println("TotalSpace --> " + f.getTotalSpace());
		System.out.println("FreeSpace --> " + f.getFreeSpace());
		
//		Thread.currentThread().join();
//		
//		System.out.println("ahahaha");
	} 
}
