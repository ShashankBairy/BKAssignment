package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.BusAssianDetails;

@Repository
public interface BusAssianRepository extends JpaRepository<BusAssianDetails, Integer> {

}
