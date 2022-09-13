package lv.javaguru.java2.atmapp.menuTemporary;

import java.util.ArrayList;
import java.util.List;

class AccountsTemp {

    private static List<String> accounts = new ArrayList<>();
    String name;
    public AccountsTemp() {
    }

    public static void main(String[] args) {
        accounts.add("Fenriz");
        accounts.add("Nocturno");
        accounts.add("Abbath");

        System.out.println(accounts.contains("Abbath"));
    }

    public AccountsTemp(String name) {
        this.name = name;
    }

    public static List<String> getAccounts() {
        return accounts;
    }

    public static void setAccounts(List<String> accounts) {
        AccountsTemp.accounts = accounts;
    }

    boolean isExist(String name) {

        return accounts.contains(name);
    }

    @Override
    public String toString() {
        return "AccountsTemp{" +
                "name='" + name + '\'' +
                '}';
    }
}
