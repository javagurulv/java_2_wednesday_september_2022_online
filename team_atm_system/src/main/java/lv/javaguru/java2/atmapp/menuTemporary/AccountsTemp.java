package lv.javaguru.java2.atmapp.menuTemporary;

import java.util.ArrayList;
import java.util.List;

public class AccountsTemp {

    //    private static ArrayList<String> accounts = new ArrayList<>();
    private List<String> accounts = new ArrayList<>();

    public AccountsTemp() {
        accounts.add("ivan");
        accounts.add("Abbath");
        accounts.add("Cvlto");
    }

    public boolean isExist(String name) {
        return accounts.contains(name);
    }

}
