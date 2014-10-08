package com.epam.hnyp.task6.subtask4;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class SearchParamContainer {
	private String fileName;
	private boolean finished;
	private SearchStatus searchStatus = new SearchStatus();
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public SearchStatus getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(SearchStatus searchStatus) {
		this.searchStatus = searchStatus;
	}
}
