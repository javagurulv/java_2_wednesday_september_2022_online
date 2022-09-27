package lv.javaguru.java2.atmapp.adminOperationsUI;

import lv.javaguru.java2.atmapp.adminOperations.FindUserByIDServiсe;

import java.util.Scanner;

public class FindUserAdminUIAction implements AdminUIactions {

    private FindUserByIDServiсe findUserByIDServise;

    public FindUserAdminUIAction(FindUserByIDServiсe findUserByIDServise) {
        this.findUserByIDServise = findUserByIDServise;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enetr user ID");
        int userID = scanner.nextInt();
        findUserByIDServise.execute(userID);
    }
}
