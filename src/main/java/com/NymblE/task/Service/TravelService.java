package com.NymblE.task.Service;

import com.NymblE.task.DTO.PassengerDetailsDto;
import com.NymblE.task.DTO.TravelPackageDetailsDto;
import com.NymblE.task.DTO.TravelPackagePassengerDetailsDto;
import com.NymblE.task.Exceptions.ActivityNotFoundException;
import com.NymblE.task.Exceptions.InsufficientBalanceException;
import com.NymblE.task.Exceptions.PassengerNotFoundException;
import com.NymblE.task.Exceptions.TravelPackageNotFoundException;
import com.NymblE.task.Model.*;

import java.util.List;

public interface TravelService {

    TravelPackageDetailsDto getTravelPackageDetails(Long travelPackageId) throws TravelPackageNotFoundException;

    TravelPackagePassengerDetailsDto getTravelPackagePassengers(Long travelPackageId) throws TravelPackageNotFoundException;

    PassengerDetailsDto getPassengerDetails(Long passengerId) throws PassengerNotFoundException;

    List<Activity> getActivitiesWithAvailableSpaces();


    TravelPackage createTravelPackage(TravelPackage travelPackage);
    Passenger createPassenger(Passenger passenger);
    Activity createActivity(Activity activity);
    Destination createDestination(Destination destination);
    PassengerActivity createPassengerActivity(Long passengerId, Long activityId) throws InsufficientBalanceException, PassengerNotFoundException, ActivityNotFoundException;
}

