package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {public Dealership getDealership() {
    Dealership dealership = null;

    try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
        String line = reader.readLine(); // Dealership info
        if (line != null) {
            String[] info = line.split("\\|");
            String name = info[0];
            String address = info[1];
            String phone = info[2];
            dealership = new Dealership(name, address, phone);
        }
        String vehicleLine;
        while ((vehicleLine = reader.readLine()) != null) {
            String[] data = vehicleLine.split("\\|");
            int vin = Integer.parseInt(data[0]);
            int year = Integer.parseInt(data[1]);
            String make = data[2];
            String model = data[3];
            String type = data[4];
            String color = data[5];
            int odometer = Integer.parseInt(data[6]);
            double price = Double.parseDouble(data[7]);

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);
        }

    } catch (IOException e) {
        System.out.println("Error reading inventory file: " + e.getMessage());
    }

    return dealership;
}

    public void saveDealership(Dealership dealership) {
    }
}


