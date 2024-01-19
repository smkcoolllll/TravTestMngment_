package com.NymblE.task.Controller;

import com.NymblE.task.DTO.PassengerDetailsDto;
import com.NymblE.task.DTO.TravelPackageDetailsDto;
import com.NymblE.task.DTO.TravelPackagePassengerDetailsDto;
import com.NymblE.task.Exceptions.ActivityNotFoundException;
import com.NymblE.task.Exceptions.InsufficientBalanceException;
import com.NymblE.task.Exceptions.PassengerNotFoundException;
import com.NymblE.task.Exceptions.TravelPackageNotFoundException;
import com.NymblE.task.Model.*;
import com.NymblE.task.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @PostMapping("/package")
    public ResponseEntity<TravelPackage> createTravelPackage(@RequestBody TravelPackage travelPackage) {
        TravelPackage createdPackage = travelService.createTravelPackage(travelPackage);
        return ResponseEntity.ok(createdPackage);
    }

    @PostMapping("/passenger")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        Passenger createdPassenger = travelService.createPassenger(passenger);
        return ResponseEntity.ok(createdPassenger);
    }

    @PostMapping("/activity")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = travelService.createActivity(activity);
        return ResponseEntity.ok(createdActivity);
    }

    @PostMapping("/destination")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        Destination createdDestination = travelService.createDestination(destination);
        return ResponseEntity.ok(createdDestination);
    }

    @PostMapping("/passenger-activity/{passengerId}/{activityId}")
    public ResponseEntity<PassengerActivity> createPassengerActivity(
            @PathVariable Long passengerId, @PathVariable Long activityId) throws PassengerNotFoundException, ActivityNotFoundException, InsufficientBalanceException {
        PassengerActivity createdPassengerActivity = travelService.createPassengerActivity(passengerId, activityId);
        return ResponseEntity.ok(createdPassengerActivity);
    }



    @GetMapping("/package/{id}/details")
    public ResponseEntity<TravelPackageDetailsDto> getTravelPackageDetails(@PathVariable Long id) throws TravelPackageNotFoundException {
        TravelPackageDetailsDto details = travelService.getTravelPackageDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/package/{id}/passengers")
    public ResponseEntity<TravelPackagePassengerDetailsDto> getTravelPackagePassengers(@PathVariable Long id) throws TravelPackageNotFoundException {
        TravelPackagePassengerDetailsDto passengers = travelService.getTravelPackagePassengers(id);
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/passenger/{id}/details")
    public ResponseEntity<PassengerDetailsDto> getPassengerDetails(@PathVariable Long id) throws PassengerNotFoundException {
        PassengerDetailsDto details = travelService.getPassengerDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/activities/available")
    public ResponseEntity<List<Activity>> getActivitiesWithAvailableSpaces() {
        List<Activity> activities = travelService.getActivitiesWithAvailableSpaces();
        return ResponseEntity.ok(activities);
    }


}
