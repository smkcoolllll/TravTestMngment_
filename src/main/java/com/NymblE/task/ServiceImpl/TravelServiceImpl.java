package com.NymblE.task.ServiceImpl;

import com.NymblE.task.DTO.PassengerDetailsDto;
import com.NymblE.task.DTO.TravelPackageDetailsDto;
import com.NymblE.task.DTO.TravelPackagePassengerDetailsDto;
import com.NymblE.task.Exceptions.ActivityNotFoundException;
import com.NymblE.task.Exceptions.InsufficientBalanceException;
import com.NymblE.task.Exceptions.PassengerNotFoundException;
import com.NymblE.task.Exceptions.TravelPackageNotFoundException;
import com.NymblE.task.Model.*;
import com.NymblE.task.Repository.*;
import com.NymblE.task.Service.TravelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private PassengerActivityRepository passengerActivityRepository;

    @Override
    public TravelPackage createTravelPackage(TravelPackage travelPackage) {
        return travelPackageRepository.save(travelPackage);
    }

    @Override
    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    @Transactional
    public PassengerActivity createPassengerActivity(Long passengerId, Long activityId) throws InsufficientBalanceException, PassengerNotFoundException, ActivityNotFoundException {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException(String.valueOf(passengerId)));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));

        if (activity.getCapacity() > 0) {
            double cost = activity.getCost();

            switch (passenger.getPassengerType()) {
                case STANDARD:
                    if (passenger.getBalance() >= cost) {
                        passenger.setBalance(passenger.getBalance() - cost);
                    } else {
                        throw new InsufficientBalanceException("Insufficient balance for standard passenger");
                    }
                    break;
                case GOLD:
                    double discountAmount = 0.1 * cost;
                    if (passenger.getBalance() >= (cost - discountAmount)) {
                        passenger.setBalance(passenger.getBalance() - (cost - discountAmount));
                    } else {
                        throw new InsufficientBalanceException("Insufficient balance for gold passenger");
                    }
                    break;
                case PREMIUM:
                    break;
                default:
                    throw new RuntimeException("Invalid passenger type");
            }

            activity.setCapacity(activity.getCapacity() - 1);

            PassengerActivity passengerActivity = new PassengerActivity();
            passengerActivity.setPassenger(passenger);
            passengerActivity.setActivity(activity);
            passengerActivityRepository.save(passengerActivity);

            return passengerActivity;
        } else {
            throw new RuntimeException("Activity is full");
        }
    }



    @Override
    public TravelPackageDetailsDto getTravelPackageDetails(Long travelPackageId) throws TravelPackageNotFoundException {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new TravelPackageNotFoundException(String.valueOf(travelPackageId)));

        return new TravelPackageDetailsDto(travelPackage);
    }

    @Override
    public TravelPackagePassengerDetailsDto getTravelPackagePassengers(Long travelPackageId) throws TravelPackageNotFoundException {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new TravelPackageNotFoundException(String.valueOf(travelPackageId)));

        return new TravelPackagePassengerDetailsDto(travelPackage);
    }

    @Override
    public PassengerDetailsDto getPassengerDetails(Long passengerId) throws PassengerNotFoundException {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException(String.valueOf(passengerId)));

        return new PassengerDetailsDto(passenger);
    }

    @Override
    public List<Activity> getActivitiesWithAvailableSpaces() {
        return activityRepository.findByCapacityGreaterThan(0);
    }
}