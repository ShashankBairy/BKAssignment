package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.BusRouteDetails;

@Repository
public interface BusRouteDetailsRepository extends JpaRepository<BusRouteDetails, Integer> {

}
