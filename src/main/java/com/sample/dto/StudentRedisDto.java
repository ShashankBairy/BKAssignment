package com.sample.dto;
 
import java.io.Serializable;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRedisDto  implements Serializable{
	private int studentId;
	private String studentName;
	private String gender;
	private String section;
	private String admission_status;
	private String father_name;
 
}