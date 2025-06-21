package com.sample.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentMajorInfo {

	private String courseFee;
    private String addiAmount;
    private String netFee;
    private String serviceTaxPaid;
    private String feePaid;
    private String feeDeducation;
    private String feeRefund;
    private String overAlldue;
    private String serviceTaxToBePaid;
    private int pocketRefund;
    private int depositedAmount;
    private int takenAmount;
    private Integer academicYear;
    private String stop;
    private Integer trStatus ;
    private double akash_books;
	private double bus_pss;
	private double caution_deposite;
	private double crd_books;
	private double miscellaneous;
	private double std_welfare_fund;
	private double material;
	private double eamcet_app_fee;
}
