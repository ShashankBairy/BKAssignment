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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name="org_campus", schema="\"org_details\"")
public class Campus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campus_id")  
    private int campusId;        
    @Column(name = "campus_code")
    private String campusCode;
    
    @Column(name = "campus_name") 
    private String campusName;
    
    @Column(name = "campus_type")
    private String campusType;

}
