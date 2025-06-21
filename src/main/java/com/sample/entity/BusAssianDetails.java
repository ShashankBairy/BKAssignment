package com.sample.entity;

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
@Table(name = "bus_assian_details", schema="`Student_Details`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusAssianDetails{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int student_id;
    
    
    @OneToOne
    @JoinColumn(name = "driver_id")
    private DriverDetails driverdetails;
    
    @OneToOne
    @JoinColumn(name = "route_id")
    private BusRouteDetails busroutedetails;
    
    @OneToOne
    @JoinColumn(name = "bus_id")
    private BusDetails busdetails;
}
 
