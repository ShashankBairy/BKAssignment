package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.BusDetails;

@Repository
public interface BusDetailsRepository extends JpaRepository<BusDetails, Integer> {

}
