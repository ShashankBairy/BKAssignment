package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.Campus;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer>{

}
