package lv.javaguru.java2.atmapp.menuTemporary;

import java.util.ArrayList;
import java.util.List;

class AccountsTemp {

    private List<String> accounts = new ArrayList<>();

    public AccountsTemp() {
		accounts.add("Fenriz");
		accounts.add("Nocturno");
		accounts.add("Abbath");
	}

    boolean isExist(String name) {
        return accounts.contains(name);
    }

}
