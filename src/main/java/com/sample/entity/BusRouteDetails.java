package com.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bus_route_details", schema="`Student_Details`")
public class BusRouteDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int route_id;
	private String bus_from;
	private String bus_to;
	private int number_of_stops;
	private String stops;
	private double fair;
}
