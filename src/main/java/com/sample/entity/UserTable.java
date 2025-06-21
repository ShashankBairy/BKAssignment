package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_table", schema="\"Student_Details\"")
public class UserTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;
	
	private String password;
	private String designation;
//	
//	@JoinColumn(name="campus_id", referencedColumnName="campus_id")
//	private Campus campus;
}
