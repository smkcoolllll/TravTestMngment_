package com.NymblE.task;

import com.NymblE.task.DTO.PassengerDetailsDto;
import com.NymblE.task.DTO.TravelPackageDetailsDto;
import com.NymblE.task.DTO.TravelPackagePassengerDetailsDto;
import com.NymblE.task.Exceptions.ActivityNotFoundException;
import com.NymblE.task.Exceptions.InsufficientBalanceException;
import com.NymblE.task.Exceptions.PassengerNotFoundException;
import com.NymblE.task.Exceptions.TravelPackageNotFoundException;
import com.NymblE.task.Model.*;
import com.NymblE.task.Repository.*;
import com.NymblE.task.ServiceImpl.TravelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelServiceImplTest {

    @InjectMocks
    private TravelServiceImpl travelService;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private PassengerActivityRepository passengerActivityRepository;

    private TravelPackage testTravelPackage;
    private Passenger testPassenger;
    private Activity testActivity;

    @BeforeEach
    void setUp() {
        // Initialize test data
        testTravelPackage = new TravelPackage();
        testTravelPackage.setId(1L);
        testTravelPackage.setName("Test Package");

        testPassenger = new Passenger();
        testPassenger.setId(1L);
        testPassenger.setName("Test Passenger");
        testPassenger.setBalance(100.0); // Set balance for testing

        testActivity = new Activity();
        testActivity.setId(1L);
        testActivity.setName("Test Activity");
        testActivity.setCapacity(10);
        testActivity.setCost(50.0); // Set cost for testing

        // Set up mock responses
        Mockito.when(travelPackageRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(testTravelPackage));
        Mockito.when(passengerRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(testPassenger));
        Mockito.when(activityRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(testActivity));
        Mockito.when(activityRepository.findByCapacityGreaterThan(Mockito.anyInt())).thenReturn(Collections.singletonList(testActivity));
    }

    @Test
    void testGetTravelPackageDetails() throws TravelPackageNotFoundException {
        TravelPackageDetailsDto details = travelService.getTravelPackageDetails(1L);
        assertNotNull(details);
        assertEquals("Test Package", details.getName());
    }

    @Test
    void testGetTravelPackagePassengers() throws TravelPackageNotFoundException {
        TravelPackagePassengerDetailsDto passengers = travelService.getTravelPackagePassengers(1L);
        assertNotNull(passengers);
        assertEquals(0, passengers.getPassengers().size());
    }

    @Test
    void testGetPassengerDetails() throws PassengerNotFoundException {
        PassengerDetailsDto details = travelService.getPassengerDetails(1L);
        assertNotNull(details);
        assertEquals("Test Passenger",details);
    }

    @Test
    void testGetActivitiesWithAvailableSpaces() {
        assertEquals(1, travelService.getActivitiesWithAvailableSpaces().size());
    }

    @Test
    void testCreatePassengerActivity() {
        try {
            PassengerActivity passengerActivity = travelService.createPassengerActivity(1L, 1L);
            assertNotNull(passengerActivity);
            assertEquals(testPassenger, passengerActivity.getPassenger());
            assertEquals(testActivity, passengerActivity.getActivity());
            assertEquals(9, testActivity.getCapacity()); // Capacity should decrease by 1
            assertEquals(50.0, testPassenger.getBalance()); // Balance should decrease by activity cost
        } catch (InsufficientBalanceException | PassengerNotFoundException | ActivityNotFoundException e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }
}

