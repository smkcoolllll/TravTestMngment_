package com.NymblE.task.DTO;

import com.NymblE.task.Model.TravelPackage;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TravelPackagePassengerDetailsDto {
    private String packageName;
    private int passengerCapacity;
    private int enrolledPassengers;
    private List<PassengerDto> passengers;

    public TravelPackagePassengerDetailsDto(TravelPackage travelPackage) {
        this.packageName = travelPackage.getName();
        this.passengerCapacity = travelPackage.getPassengerCapacity();
        this.enrolledPassengers = travelPackage.getPassengers().size();
        this.passengers = travelPackage.getPassengers().stream()
                .map(PassengerDto::new)
                .collect(Collectors.toList());
    }
}
