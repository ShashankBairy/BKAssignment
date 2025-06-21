package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.OtherFeeHeads;
import com.sample.entity.StudentDetails;

@Repository
public interface OtherFeeHeadsRepository extends JpaRepository<OtherFeeHeads, Integer> {

	Optional<OtherFeeHeads> findByStudentDetails(StudentDetails student);
}
