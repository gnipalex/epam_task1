package com.epam.hnyp.task6.subtask3;

public class SearchStatus {
	private boolean error;
	private String errorMessage;
	private int offsetFirst;
	private int offsetSecond;
	private int length;

	public SearchStatus() {
	}

	/**
	 * Creates copy of status
	 * 
	 * @param st
	 */
	public SearchStatus(SearchStatus st) {
		this.error = st.error;
		this.errorMessage = st.errorMessage;
		this.length = st.length;
		this.offsetFirst = st.offsetFirst;
		this.offsetSecond = st.offsetSecond;
	}

	public int getOffsetFirst() {
		return offsetFirst;
	}

	public int getOffsetSecond() {
		return offsetSecond;
	}

	public int getLength() {
		return length;
	}

	public boolean isError() {
		return error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setError(boolean error) {
		this.error = error;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setOffsetFirst(int offsetFirst) {
		this.offsetFirst = offsetFirst;
	}

	public void setOffsetSecond(int offsetSecond) {
		this.offsetSecond = offsetSecond;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "offset(1) = [" + offsetFirst + "], offset(2) = [" + offsetSecond + "], lenght = " + length;
	}
}
