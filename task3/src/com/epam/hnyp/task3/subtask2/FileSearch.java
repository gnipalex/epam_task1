package com.epam.hnyp.task3.subtask2;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.epam.hnyp.task3.subtask2.condition.BaseFileCondition;

/**
 * Represents files searcher
 * @author Oleksandr_Hnyp
 *
 */
public class FileSearch {
	private File rootDir;
	private BaseFileCondition filterChain;
	
	/**
	 * Creates FileSearch item
	 * @param dir directory to search in
	 * @param filterChain BaseFileCondition file filter condition, or null if there's no filter
	 * @throws IllegalArgumentException if specified file is not directory or doesn't exist
	 */
	public FileSearch(File dir, BaseFileCondition filterChain) {
		if (!dir.isDirectory() || !dir.exists()) {
			throw new IllegalArgumentException();
		}
		this.rootDir = dir;
		this.filterChain = filterChain;
	}
	
	public List<File> find() {
		List<File> files = new LinkedList<>();
		if (rootDir.isDirectory() && rootDir.canRead()) {
			File[] filesInDir = rootDir.listFiles();
			for (File f : filesInDir) {
				if (f.getName().equals(".") || f.getName().equals("..")) {
					continue;
				}
				if (f.isDirectory()) {
					FileSearch fsearch = new FileSearch(f, filterChain);
					List<File> foundedDeep = fsearch.find();
					files.addAll(foundedDeep);
					continue;
				}
				if (filterChain == null) {
					files.add(f);
					continue;
				}
				if (filterChain.satisfies(f)) {
					files.add(f);
				}
			}
		}
		return files;
	}
}
