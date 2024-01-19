package com.NymblE.task.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter



public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int passengerCapacity;

    @ElementCollection
    private List<String> itinerary;

//    @OneToMany(mappedBy = "travelPackage")
//    private List<Passenger> passengers;


    @JsonIgnore  // Ignore the passengers field to break the loop
    @ManyToMany
    @JoinTable(
            name = "travel_package_passenger",
            joinColumns = @JoinColumn(name = "travel_package_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "travelPackage")
    private List<Destination> destinations;
}
