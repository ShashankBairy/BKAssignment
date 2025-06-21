package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.StudentDetails;
import com.sample.entity.StudentTransportDetails;

@Repository
public interface StudentTransportRepository extends JpaRepository<StudentTransportDetails, Integer> {

	List<StudentTransportDetails> findByStudentDetails(StudentDetails studentDetails);
}
