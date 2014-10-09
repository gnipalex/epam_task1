package com.epam.hnyp.task6.subtask4;

import com.epam.hnyp.task6.subtask3.SearchStatus;

public class SearchStatusExtended extends SearchStatus {
	private boolean finish;
	
	public SearchStatusExtended() {
	}

	public SearchStatusExtended(SearchStatusExtended st) {
		super(st);
		this.finish = st.finish;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
}
