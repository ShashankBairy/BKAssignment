package com.sample.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashPaymentDto {
	
	private int amount;
	private String class_type;
	private int feeHeadId;
	private int pocket_money_amount;
	private String modeOfPayment;
	private int pre_print_reciept_no;
	private String bank_details;
	private String amountIn;
	private String akashBooks;
	private String description;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fee_payment_year;

}
