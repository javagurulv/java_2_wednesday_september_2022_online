package lv.javaguru.java2.atmapp.adminServicesUI;

import lv.javaguru.java2.atmapp.adminServices.FindUserByIDServiсe;

import java.util.Scanner;

public class FindUserAdminUIAction implements AdminUIactions {

    private FindUserByIDServiсe findUserByIDService;

    public FindUserAdminUIAction(FindUserByIDServiсe findUserByIDService) {
        this.findUserByIDService = findUserByIDService;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user ID");
        int userID = scanner.nextInt();
        findUserByIDService.execute(userID);
    }
}
