package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Table(name="additional_details", schema="`Student_Details`")
public class AdditionalDetails {
 
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    private String language1;
    private String language2;
    private String language3;
 
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentDetails studentDetails;
}