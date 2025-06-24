package com.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "student_profile", schema = "\"Student_Details\"")
public class StudentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;
    
	@Column(name="student_name")
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
	private String type_class;
	private int installment_1;
	private int installment_2;
	private int installment_3;

//    private date created_on;
//    private String created_by;
//    private date updated_on;
//    private String updated_by;

//	@OneToOne
//	private AdditionalDetails addtionalDetails;
//	
//	
//	@OneToMany
//	private Payments payments;

}
