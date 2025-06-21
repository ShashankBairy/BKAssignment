package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="student_bank", schema="`Student_Details`")
public class StudentBank{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="student_id", referencedColumnName="student_id")
	private StudentDetails studentDetails;
	private String bank_name;
	private String ifsc_code;
	private String branch_name;
	private String city_name;
//	private date created_on;
//	private String created_by;
//	private date updated_on;
//	private String updated_by;
	
 
}