	package com.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pocket_money_id;
	
	@Column(name="pocket_refund")
	private int pocketRefund;
	@Column(name="deposited_amount")
    private int depositedAmount;
	
	@Column(name="taken_amount")
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
