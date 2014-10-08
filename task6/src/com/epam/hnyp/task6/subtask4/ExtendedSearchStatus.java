package com.epam.hnyp.task6.subtask4;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class ExtendedSearchStatus extends SearchStatus {
	private String fileName;
	private boolean finished;

	public ExtendedSearchStatus() {
	}

	public ExtendedSearchStatus(ExtendedSearchStatus st) {
		super(st);
		this.fileName = st.fileName;
		this.finished = st.finished;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
