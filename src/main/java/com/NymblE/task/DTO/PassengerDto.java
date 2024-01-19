package com.NymblE.task.DTO;

import com.NymblE.task.Model.Passenger;
import lombok.Data;

@Data
public class PassengerDto {
    private String passengerName;
    private int passengerNumber;

    public PassengerDto(Passenger passenger) {
        this.passengerName = passenger.getName();
        this.passengerNumber = passenger.getPassengerNumber();
    }
}