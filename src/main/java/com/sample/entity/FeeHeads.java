package com.sample.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fee_heads",schema="`Student_Details`")
public class FeeHeads {
	
	@Id
	@Column(name="fee_id")
    private  int fee_id;
    private String fee_type;
//  private  Date created_on;
// private  String created_by;
// private Date updated_on;
// private String updated_by;
   
   
}