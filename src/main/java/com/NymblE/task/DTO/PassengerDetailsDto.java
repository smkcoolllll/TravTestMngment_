package com.NymblE.task.DTO;

import com.NymblE.task.Model.Passenger;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PassengerDetailsDto {
    private String passengerName;
    private int passengerNumber;
    private double balance;
    private List<PassengerActivityDto> activities;

    public PassengerDetailsDto(Passenger passenger) {
        this.passengerName = passenger.getName();
        this.passengerNumber = passenger.getPassengerNumber();
        this.balance = passenger.getBalance();
        this.activities = passenger.getPassengerActivities().stream()
                .map(PassengerActivityDto::new)
                .collect(Collectors.toList());
    }
}