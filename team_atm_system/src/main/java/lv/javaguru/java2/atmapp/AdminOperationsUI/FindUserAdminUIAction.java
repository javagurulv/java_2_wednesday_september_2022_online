package lv.javaguru.java2.atmapp.AdminOperationsUI;

import lv.javaguru.java2.atmapp.AdminOperations.FindUserByIDServise;

import java.util.Scanner;

public class FindUserAdminUIAction implements AdminUIactions {

    private FindUserByIDServise findUserByIDServise;

    public FindUserAdminUIAction(FindUserByIDServise findUserByIDServise) {
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
