package com.op.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient")
public class Patient {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long patientId;

	private String patientName;

	private String patientContactDetails;

	public Patient() {
	}

	public Patient(int patientId, String patientName, String patientContactDetails) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientContactDetails = patientContactDetails;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientContactDetails() {
		return patientContactDetails;
	}

	public void setPatientContactDetails(String patientContactDetails) {
		this.patientContactDetails = patientContactDetails;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + "]";
	}

}
