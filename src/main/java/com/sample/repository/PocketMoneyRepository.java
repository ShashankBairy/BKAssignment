package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.PocketMoney;

@Repository
public interface PocketMoneyRepository extends JpaRepository<PocketMoney, Integer> {

}
