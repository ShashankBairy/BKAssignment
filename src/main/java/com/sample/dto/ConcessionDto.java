package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcessionDto {
	
	private String joinInto;
	private String reason;
	private String approvedBy;
	private int concessionAmount;
	private String reasonDescription;

}
