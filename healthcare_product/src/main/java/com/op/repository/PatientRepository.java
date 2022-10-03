package com.op.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.op.model.Patient;

public interface PatientRepository extends MongoRepository<Patient, Long> {

	
}
