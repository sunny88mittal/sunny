package com.cleartax.base;
public final class Token {

	private final int number;
	
	private ProcessState processState;
	
	private int timeConsumed = 0;
	
	private Status status;
	
	public static enum Status {
		WAITING,
		PROCESSING
	}
	
	public Token(int number) {
		this.number = number;
		processState = ProcessState.DOCUMENT_VERIFICATION;
		status = Status.WAITING;
	}

	public ProcessState getProcessState() {
		return processState;
	}

	public void setProcessState(ProcessState processState) {
		this.processState = processState;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public int getTimeConsumed() {
		return timeConsumed;
	}

	public void setTimeConsumed(int timeConsumed) {
		this.timeConsumed = timeConsumed;
	}
}
