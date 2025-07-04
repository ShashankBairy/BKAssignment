package com.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fee_details", schema="`Student_Details`")
public class FeeDetails {
 
    @Id
    private int course_fee_id;
 
    private String course_fee;
    @Column(name="add_i_amount")
    private String addiAmount;
 
    private String net_fee;
    private String service_tax_paid;
    private String fee_paid;
    @Column(name="fee_deduction")
    private String feeDeucation;
    private String fee_refund;
    @Column(name="over_all_due")
    private String overAlldue;
    private String service_tax_to_be_paid;
    
    @OneToOne
    @JoinColumn(name="fee_head_id", referencedColumnName="fee_id")
    private FeeHeads feeHeads;
    
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentDetails studentDetails;
    
  
}