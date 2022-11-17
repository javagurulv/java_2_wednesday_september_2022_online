package apartment.console_ui;

import apartment.core.services.PrintAllApartmentService;

public class PrintAllApartmentUIUIAction implements UIAction {

    private PrintAllApartmentService printAllApartmentService;
    public PrintAllApartmentUIUIAction(PrintAllApartmentService printAllApartmentService) {

        this.printAllApartmentService = printAllApartmentService;
    }

    @Override
    public void execute(){
        System.out.println("Apartments list: ");
        printAllApartmentService.execute().forEach(System.out::println);
        System.out.println("Apartments list end.");
    }

/*@Override
    public void execute (List<Apartment> apartmentList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Apartment list: ");
        for (Apartment apartment : apartmentList) {
            System.out.println(apartment);
        }
        System.out.println("Apartment list end.");
    }*/

}
