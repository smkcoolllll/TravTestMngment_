package com.NymblE.task.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int passengerNumber;

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

    @OneToMany(mappedBy = "passenger")
    private List<PassengerActivity> passengerActivities;
}