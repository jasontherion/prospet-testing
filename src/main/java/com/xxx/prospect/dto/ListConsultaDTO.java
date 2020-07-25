package com.xxx.prospect.dto;


public class ListConsultaDTO {


	private QueryStatusDTO QueryStatus;

	private StatusRequestDTO Status;

	public QueryStatusDTO getQueryStatus() {
		return QueryStatus;
	}

	public void setQueryStatus(QueryStatusDTO queryStatus) {
		this.QueryStatus = queryStatus;
	}

	public StatusRequestDTO getStatus() {
		return Status;
	}

	public void setStatus(StatusRequestDTO status) {
		Status = status;
	}
/*
	@Override
	public String toString() {
		return "ListConsultaDTO [QueryStatus=" + QueryStatus + ", Status=" + Status + "]";
	}
*/
}
