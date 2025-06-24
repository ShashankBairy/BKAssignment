package com.sample.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.CampusDetails;
import com.sample.entity.StudentDetails;

@Repository
public interface CampusDetailsRepository extends JpaRepository<CampusDetails, Integer> {

	List<CampusDetails> findByStudentDetails(StudentDetails student);
}
