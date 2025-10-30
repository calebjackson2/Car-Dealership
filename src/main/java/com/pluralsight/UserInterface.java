package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        boolean running = true;

        while (running) {
            System.out.println("\n=== DEALERSHIP MENU ===");
            System.out.println("1. Show All Vehicles");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> processAllVehiclesRequest();
                case 2 -> processAddVehicleRequest();
                case 3 -> processRemoveVehicleRequest();
                case 4 -> running = false;
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {
        System.out.println("Enter Vehicle Info:");
        System.out.print("VIN: "); int vin = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: "); String make = scanner.nextLine();
        System.out.print("Model: "); String model = scanner.nextLine();
        System.out.print("Year: "); int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Color: "); String color = scanner.nextLine();
        System.out.print("Odometer: "); int odometer = scanner.nextInt();
        System.out.print("Price: "); double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, make, model, year, color, odometer, price);
        dealership.addVehicle(vehicle);

        new DealershipFileManager().saveDealership(dealership);
        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN to remove: ");
        int vin = scanner.nextInt();
        Vehicle toRemove = null;

        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }
        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            new DealershipFileManager().saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }
}