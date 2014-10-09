package com.epam.hnyp.task6.subtask4;

import java.util.Queue;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class SearchParamContainer {
	private String fileName;
	private final Queue<SearchStatusExtended> statusesQueue;
	
	public SearchParamContainer(Queue<SearchStatusExtended> statusesQueue) {
		this.statusesQueue = statusesQueue;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Queue<SearchStatusExtended> getStatusesQueue() {
		return statusesQueue;
	}
}
