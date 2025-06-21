package com.sample.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryDto {
	
	private String class_type;
	private String academic_year;
	private String payment_head;
	private String mode_of_payment;
	private String fee_head;
	private int amount;
	private String payment_campus;;
	private Date date;
    private int installment_no;	

}
