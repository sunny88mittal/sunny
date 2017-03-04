package com.cleartax.base;

public enum ProcessState {
	DOCUMENT_VERIFICATION, POLICE_VERIFICATION, BIOMETRIC_COLLECTION, PROCESS_FINISHED;

	public static ProcessState getNextState(ProcessState processState) {
		switch (processState) {
		case DOCUMENT_VERIFICATION:
			return POLICE_VERIFICATION;
		case POLICE_VERIFICATION:
			return BIOMETRIC_COLLECTION;
		case BIOMETRIC_COLLECTION:
			return PROCESS_FINISHED;
		default:
			return null;
		}
	}
}