package com.pluralsight;

import java.util.ArrayList;

public class Dealership {private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>(); // Important: initialize inventory
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }


    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max)
    { return null; }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model)
    { return null; }
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max)
    { return null; }
    public ArrayList<Vehicle> getVehiclesByColor(String color)
    { return null; }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max)
    { return null; }
    public ArrayList<Vehicle> getVehiclesByType(String type)
    { return null; }

    public void removeVehicle(Vehicle vehicle) {
        // Empty for now
    }

    // Getters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}
