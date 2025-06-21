package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPerformance {
	
	private int ipeMarks;
	private int recentMarks;
	private int emcetMockTest;
	private String attendence;

}
