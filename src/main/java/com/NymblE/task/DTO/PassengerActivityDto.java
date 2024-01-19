package com.NymblE.task.DTO;

import com.NymblE.task.Model.PassengerActivity;
import lombok.Data;

@Data
public class PassengerActivityDto {
    private String activityName;
    private String destinationName;
    private double activityCost;

    public PassengerActivityDto(PassengerActivity passengerActivity) {
        this.activityName = passengerActivity.getActivity().getName();
        this.destinationName = passengerActivity.getActivity().getDestination().getName();
        this.activityCost = passengerActivity.getActivity().getCost();
    }
}