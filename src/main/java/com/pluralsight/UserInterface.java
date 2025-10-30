package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline
            switch (choice) {
                case 7 -> processAllVehiclesRequest();
                case 99 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Option not implemented yet.");
            }
        }
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }
    private void displayMenu() {
        System.out.println("""
                ========== DEALERSHIP MENU ==========
                1 - Find vehicles within price range
                2 - Find vehicles by make/model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                =====================================
                Enter your choice: """);
    }

    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }
}