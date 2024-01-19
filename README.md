# Spring Boot Travel Management System

## Overview

This Spring Boot project implements a Travel Management System with the following features:

1. **Entities:**
   - **TravelPackage:** Represents a travel package with a name, passenger capacity, itinerary, and list of passengers.
   - **Destination:** Represents a destination with a name and a list of activities available.
   - **Activity:** Represents an activity with a name, description, cost, and capacity, available at a specific destination.
   - **Passenger:** Represents a passenger with a name, passenger number, balance (for standard and gold passengers), and a list of signed-up activities.
   
2. **Passenger Types:**
   - Standard, Gold, and Premium passengers with varying rules for signing up and payment.

3. **Functionality:**
   - Print itinerary of a travel package.
   - Print the passenger list of a travel package.
   - Print details of an individual passenger.
   - Print details of activities with available spaces.

## Guidelines and Principles

1. **SOLID Design Principles:**
   - Code follows SOLID principles to ensure maintainability and extensibility.

2. **Clean Code Guidelines:**
   - Code adheres to Clean Code guidelines for readability and maintainability.
   - Dependency Injection is used where applicable.

3. **Unit Test Guidelines:**
   - Unit tests are written following best practices to ensure code reliability.
   - JUnit best practices are followed for effective testing.

4. **Java Code Documentation:**
   - Code is thoroughly documented using JavaDoc comments to enhance readability and understanding.
