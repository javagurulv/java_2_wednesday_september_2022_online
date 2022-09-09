package lv.javaguru.java2.rentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CarRentalApplication {

    public static void main(String[] args) {
        List<Vehicle> vehicles  = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add vehicle to list");
            System.out.println("2. Delete vehicle from list");
            System.out.println("3. Show all vehicles in the list");
            System.out.println("4. Exit");

            System.out.println("");

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1 -> {
                    System.out.println("Choose vehicle type\n" +
                            "1. Car\n" +
                            "2. Bus\n" +
                            "3. Motorcycle");
                    int chosenType = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter brand: ");
                    String brand = scanner.nextLine();
                    System.out.println("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.println("Enter year of production: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter color: ");
                    String color = scanner.nextLine();
                    System.out.println("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter engine type: ");
                    String engineType = scanner.nextLine();
                    System.out.println("Enter plate number: ");
                    String plateNumber = scanner.nextLine();
                    System.out.println("Enter transmission type: ");
                    String transmissionType = scanner.nextLine();
                    if (chosenType == 1) {
                        Vehicle car = new Car(brand, model, false, year, color, price, engineType, plateNumber, transmissionType);
                        vehicles.add(car);
                    } else if (chosenType == 2) {
                        Vehicle bus = new Bus(brand, model, false, year, color, price, engineType, plateNumber, transmissionType);
                        vehicles.add(bus);
                    } else if (chosenType == 3) {
                        Vehicle motorcycle = new Motorcycle(brand, model, false, year, color, price, engineType, plateNumber, transmissionType);
                        vehicles.add(motorcycle);
                    }
                    System.out.println("Your vehicle was added to list.");
                    break;
                }
                case 2 -> {
                    System.out.println("Enter vehicle plate number: ");
                    String plateNumber = scanner.nextLine();
                    vehicles.removeIf(x -> plateNumber.equals(x.getPlateNumber()));
                    System.out.println("Your vehicle was removed from list.");
                    break;
                }
                case 3 -> {
                    System.out.println("Vehicle list: ");
                    vehicles.forEach(System.out::println);
                    System.out.println("Vehicle list end.");
                    break;
                }
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }

    }
}
