package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.AdditionalDetails;
import com.sample.entity.StudentDetails;

@Repository
public interface AdditionalDetailsRepository extends JpaRepository<AdditionalDetails, Integer> {

	AdditionalDetails findByStudentDetails(StudentDetails student);
}
