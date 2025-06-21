package com.sample.entity;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "student_transport_details", schema="`Student_Details`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTransportDetails {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id")
    private Integer trId;
 
    
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName="student_id")
    private StudentDetails studentDetails;
 
    @Column(name = "acadamic_year")
    private Integer academicYear;
 
//    @Column(name = "bus_id")
//    private Integer busId;
// 
//    @Column(name = "driver_id")
//    private Integer driverId;
// 
//    @Column(name = "route_id")
//    private Integer routeId;
 
    @Column(name = "stop")
    private String stop;
 
    @Column(name = "tr_status")
    private Integer trStatus ;
 
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
    
    
////mappings   
  
    
    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName="driver_id")
    private DriverDetails driverdetails;
    
    @OneToOne
    @JoinColumn(name = "route_id", referencedColumnName="route_id")
    private BusRouteDetails busroutedetails;
    
    @OneToOne
    @JoinColumn(name = "bus_id", referencedColumnName="bus_id")
    private BusDetails busdetails;
    
}
