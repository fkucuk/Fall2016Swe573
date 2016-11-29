package com.fkucuk.model;

import java.util.List;

public class ActivitySearch {

	private int durationFrom;
	private int durationTo;
	
	private List<String> descriptions;
	
	private ActivitySearchType searchType;

	public ActivitySearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(ActivitySearchType searchType) {
		this.searchType = searchType;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public int getDurationFrom() {
		return durationFrom;
	}

	public int getDurationTo() {
		return durationTo;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public void setDurationFrom(int durationFrom) {
		this.durationFrom = durationFrom;
	}

	public void setDurationTo(int durationTo) {
		this.durationTo = durationTo;
	}
	
}
