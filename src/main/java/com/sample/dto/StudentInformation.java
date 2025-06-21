package com.sample.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInformation {
	
	private String courseFee;
    private String addiAmount;
    private String netFee;
    private String serviceTaxPaid;
    private String feePaid;
    private String feeDeucation;
    private String feeRefund;
    private String overAlldue;
    private String serviceTaxToBePaid;
    private int pocketRefund;
    private int depositedAmount;
    private int takenAmount;
    


}
