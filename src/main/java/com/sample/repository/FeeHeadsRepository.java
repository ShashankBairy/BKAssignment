package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.FeeHeads;

public interface FeeHeadsRepository extends JpaRepository<FeeHeads, Integer> {

}
