package com.san.db;

import java.util.List;

import com.datastax.driver.core.PagingState;

public class PaginatedData<T> {

	public PaginatedData(List<T> records, PagingState pagingState) {
		this.records = records;
		this.pagingState = pagingState;
	}

	private PagingState pagingState;
	private List<T> records;

	public PagingState getPagingState() {
		return pagingState;
	}

	public void setPagingState(PagingState pagingState) {
		this.pagingState = pagingState;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "PaginatedData [pagingState=" + pagingState + ", records=" + records + "]";
	}

}
