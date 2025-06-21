package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.entity.StudentBank;

public interface StudentBankRepository extends JpaRepository<StudentBank, Integer> {

}
