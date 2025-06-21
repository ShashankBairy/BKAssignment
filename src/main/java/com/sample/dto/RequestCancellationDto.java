package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCancellationDto {
	
	private int receiptNo;
	private int prePaidreceiptNo;
	private Integer paidAmount;
	private String notes;

}
