package com.sample.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.CampusDetails;
import com.sample.entity.StudentDetails;

@Repository
public interface CampusDetailsRepository extends JpaRepository<CampusDetails, Integer> {

	CampusDetails findByStudentDetails(StudentDetails student);
}
