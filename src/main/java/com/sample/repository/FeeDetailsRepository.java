package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.FeeDetails;
import com.sample.entity.StudentDetails;

@Repository
public interface FeeDetailsRepository extends JpaRepository<FeeDetails, Integer> {

	Optional<FeeDetails> findByStudentDetails(StudentDetails student);
}
