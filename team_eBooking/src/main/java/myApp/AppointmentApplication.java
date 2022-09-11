package myApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppointmentApplication {
    public static void main(String[] args) {

        List<Appointment> appointments = new ArrayList<>();
        while (true) {
            System.out.println("Appointment List");
            System.out.println("");
            System.out.println("1. Add appointment");
            System.out.println("2. Delete appointment");
            System.out.println("3. Show all appointments");
            System.out.println("4. Exit");

            System.out.println("");
            System.out.println("Choose menu number to execute");
            Scanner scanner = new Scanner(System.in);

            int clientChoice = Integer.parseInt(scanner.nextLine());

            switch (clientChoice){
                case 1:{
                    System.out.println("Choose master name");
                    String masterName = scanner.nextLine();
                    System.out.println("Choose type of service");
                    String typeOfService = scanner.nextLine();
                    System.out.println("Choose available date");
                    Date date = new Date();

                    Appointment appointment = new Appointment(masterName,typeOfService,date);
                    appointments.add(appointment);

                    System.out.println("Appointment added to the list");
                    break;
                }
                case 2: {

                    System.out.println("Choose master name");
                    String masterName = scanner.nextLine();
                    System.out.println("Choose type of service");
                    String typeOfService = scanner.nextLine();
                    System.out.println("Choose available date");
                    Date date = new Date();
                    appointments.remove(new Appointment(masterName,typeOfService,date));

                    System.out.println("Appointment removed from the list");
                    break;
                }
                case 3: {
                    System.out.println("Check all appointments");
                    if(appointments.isEmpty()){
                        System.out.println("List is empty");
                    } else {
                        appointments.forEach(System.out::println);
                    }
                    break;

                }
                case 4: {
                    System.out.println("Application closed");
                    System.exit(0);
                }
            }
        }
    }
}
