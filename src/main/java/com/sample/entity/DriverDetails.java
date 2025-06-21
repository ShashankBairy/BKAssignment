package com.sample.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Table(name = "driver_details", schema="`Student_Details`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDetails{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;
 
    @Column(name = "driver_name")
    private String driverName;
 
    @Column(name = "mobile_no")
    private Long mobileNo;
 
    @OneToOne
    @JoinColumn(name="bus_id", referencedColumnName ="bus_id")
    private BusDetails busDetails;
 
    @Column(name = "is_active")
    private int isActive ;
 
//    @Column(name = "created_on", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
//    private LocalDateTime createdOn;
// 
//    @Column(name = "created_by", columnDefinition = "varchar(50) default CURRENT_USER")
//    private String createdBy;
// 
//    @Column(name = "updated_on")
//    private LocalDateTime updatedOn;
 
//    @Column(name = "updated_by")
//    private String updatedBy;
}