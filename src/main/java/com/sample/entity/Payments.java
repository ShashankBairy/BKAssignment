package com.sample.entity;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="payments", schema="`Student_Details`")
public class Payments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_serial_no;
	
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private StudentDetails studentDetails;
    
    @Column(name="class_type")
	private String classType;
	
	private String fee_heads;
	private Date fee_payment_year;
	private int amount;
	private String mode_of_payment;
	private int total_due;
	
	@OneToOne
	@JoinColumn(name="pocket_money", referencedColumnName="pocket_money_id")
	private PocketMoney pocketMoney;
	
	@ManyToOne
	@JoinColumn(name="fee_head_id", referencedColumnName="fee_id")
	private FeeHeads feeHeadId;
	private String uniform_print;
	private int pre_paid_reciept_no;
	private int paid_reciept_no;
	private String student_transfer;
	private String fee_cancellation_request;
	private String fee_cancellation_request_status;
	@Column(name="cancession_amount")
	private int concession_amount;
	private String approved_by;
	
	private String bank_details;
	
	@OneToOne
	@JoinColumn(name="academic_year",referencedColumnName= "id")
	private AcademicYear academicYear;
	
	private String payment_head;
	
	@OneToOne
    @JoinColumn(name="campus_id", referencedColumnName="campus_id")
    private Campus campus;
	
	private int installment_no;
	private String remarks;
	private Date created_on;
//	private String created_by;
//	private date updated_on;
//	private String updated_by;
	private int payment_status;
	private Integer requested_amount;
	

}
