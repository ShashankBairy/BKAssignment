	package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name="pocket_money", schema="Student_Details")
public class PocketMoney {
	@Id
	private int pocket_money_id;
	
	private int pocketRefund;
    private int depositedAmount;
    private int takenAmount;
    
    @ManyToOne
    @JoinColumn(name="fee_head_id", referencedColumnName="fee_id")
    private FeeHeads feeHeads;
    
//    @ManyToOne
//    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
//    private StudentDetails studentDetails;
    
//    private date created_on;
//	private String created_by;
//	private date updated_on;
//	private String updated_by;
    

}
