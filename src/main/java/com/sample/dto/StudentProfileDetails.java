package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDetails {

	private String studentName;
	private String gender;
	private String email;
	private String dob;
	private int ipe_marks;
	private int recent_marks;
	private int emcet_mock_test;
	private String attendance;
	private String group_name;
	private String course_track;
	private String section;
	private String admission_status;
	private String admission_type;
	private String student_type;
	private int concession;
	private int aadhar_no;
	private String mother_name;
	private String father_name;
	private int mobile_no;
	private String address;
	private String language1;
	private String language2;
	private String language3;
	private String city;
    private String campusName;
    private String type_class;
	
}
