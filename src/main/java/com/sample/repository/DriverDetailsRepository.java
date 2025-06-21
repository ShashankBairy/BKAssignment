package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.DriverDetails;

@Repository
public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Integer> {

}
