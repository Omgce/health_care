package com.op.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.op.model.Patient;
import com.op.repository.PatientRepository;
import com.op.service.SequenceGeneratorService;

@RestController
public class PatientController {

	@Autowired
	private PatientRepository repo;

	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/patients")
	public List<Patient> getAllPatients() {

		return repo.findAll();
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") long patientId) throws Throwable {
		Patient patient = repo.findById(patientId)
				.orElseThrow(() -> new Throwable("Patient not found for this id :: " + patientId));
		return ResponseEntity.ok().body(patient);
	}

	@PostMapping("/patients")
	public Patient addPatient(@Validated @RequestBody Patient patiient) {
		patiient.setPatientId(service.generateSequence(Patient.SEQUENCE_NAME));
		return repo.save(patiient);
	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long patientId,
			@Validated @RequestBody Patient patient) throws Throwable {
		Patient patient2 = repo.findById(patientId)
				.orElseThrow(() -> new Throwable("Patient not found for this id :: " + patientId));

		patient2.setPatientName(patient.getPatientName());
		patient2.setPatientContactDetails(patient.getPatientContactDetails());
		Patient update = repo.save(patient2);
		return ResponseEntity.ok(update);
	}

	@DeleteMapping("/patients/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long patientId) throws Throwable {
		Patient patient = repo.findById(patientId)
				.orElseThrow(() -> new Throwable("Patient not found for this id :: " + patientId));

		repo.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

}
