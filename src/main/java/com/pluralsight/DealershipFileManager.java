package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("dealership.csv"))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] info = line.split("\\|");
                if (info.length >= 3) {
                    String name = info[0];
                    String address = info[1];
                    String phone = info[2];
                    dealership = new Dealership(name, address, phone);
                } else {
                    dealership = new Dealership("Unknown Dealership", "N/A", "N/A");
                }
            } else {
                dealership = new Dealership("Default Dealership", "123 Default St", "000-0000");
            }

            String vehicleLine;
            while ((vehicleLine = reader.readLine()) != null) {
                if (vehicleLine.trim().isEmpty()) continue; // skip blank lines

                String[] data = vehicleLine.split("\\|");
                if (data.length < 8) continue; // skip malformed lines

                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String type = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                Vehicle vehicle = new Vehicle(vin, make, model, year, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

        } catch (FileNotFoundException e) {
            System.out.println("dealership.csv not found. Creating empty dealership...");
            dealership = new Dealership("Default Dealership", "123 Default St", "000-0000");
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            dealership = new Dealership("Error Dealership", "Unknown", "000-0000");
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("dealership.csv"))) {
            pw.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            for (Vehicle v : dealership.getAllVehicles()) {
                pw.println(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}