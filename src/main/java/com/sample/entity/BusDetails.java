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
@Table(name = "bus_details", schema="`Student_Details`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDetails {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;
 
    @Column(name = "bus_number")
    private String busNumber;
 
    @Column(name = "bus_status")
    private Integer busStatus = 0;
 
    @OneToOne
    @JoinColumn(name="bus_route_id", referencedColumnName ="route_id")
    private BusRouteDetails busRouteDetails;
 
//    @Column(name = "created_on", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
//    private LocalDateTime createdOn;
// 
//    @Column(name = "created_by", columnDefinition = "varchar(50) default CURRENT_USER")
//    private String createdBy;
// 
//    @Column(name = "updated_on")
//    private LocalDateTime updatedOn;
// 
//    @Column(name = "updated_by")
//    private String updatedBy;
}
