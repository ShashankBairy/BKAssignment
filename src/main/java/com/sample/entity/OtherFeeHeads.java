package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="other_fee_heads", schema="Student_Details")
public class OtherFeeHeads {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentDetails studentDetails;
	
	private double akash_books;
	private double bus_pss;
	private double caution_deposite;
	private double crd_books;
	private double miscellaneous;
	private double std_welfare_fund;
	private double material;
	private double eamcet_app_fee;
}
