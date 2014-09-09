package com.epam.hnyp.task3.subtask1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextLineViewer implements Iterable<String> {

	private String fileName;

	public TextLineViewer(String fname) {
		this.fileName = fname;
	}

	/**
	 * @throws IllegalArgumentException if file not found
	 */
	@Override
	public Iterator<String> iterator() {
		try {
			return new MyLineIterator();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private class MyLineIterator implements Iterator<String> {
		private Scanner scanner;
		private boolean isClosed;

		public MyLineIterator() throws FileNotFoundException {
			File file = new File(TextLineViewer.this.fileName);
			scanner = new Scanner(file);
		}

		@Override
		public boolean hasNext() {
			if (isClosed) {
				return false;
			}
			isClosed = !scanner.hasNextLine();
			if (isClosed) {
				scanner.close();
			}
			return !isClosed;
		}

		@Override
		public String next() {
			if (isClosed) {
				throw new NoSuchElementException();
			}
			String line = null;
			try {
				line = scanner.nextLine();
			} catch (NoSuchElementException e) {
				isClosed = true;
				scanner.close();
				throw e;
			}
			return line;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		protected void finalize() throws Throwable {
			scanner.close();
			super.finalize();
		}
	}
}
