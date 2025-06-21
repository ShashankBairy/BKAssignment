package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name="campus_details", schema="`Student_Details`")
public class CampusDetails {
 
    @Id
    private int id;
    
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentDetails studentDetails;
    
    private int city_id;
    private int campus_id;
 
    private String city;
    private String campusName;
//    private date created_on;
//	private String created_by;
//	private date updated_on;
//	private String updated_by;
 
    
}