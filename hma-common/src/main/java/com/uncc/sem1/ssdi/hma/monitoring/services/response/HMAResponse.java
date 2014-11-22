package com.uncc.sem1.ssdi.hma.monitoring.services.response;

public class HMAResponse {
	private String responseMsg;
	private Status status = Status.SUCCESS;

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public HMAResponse() {
		super();
	}

	public HMAResponse(String responseMsg, Status status) {
		super();
		this.responseMsg = responseMsg;
		this.status = status;
	}

}
