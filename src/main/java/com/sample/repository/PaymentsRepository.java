package com.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.Payments;
import com.sample.entity.StudentDetails;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
   
	List<Payments> findByStudentDetails(StudentDetails student);
	
	List<Payments> findByStudentDetailsAndClassType(StudentDetails studentDetails, String classType);
}

