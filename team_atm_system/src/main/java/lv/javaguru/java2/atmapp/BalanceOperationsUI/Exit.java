package lv.javaguru.java2.atmapp.BalanceOperationsUI;

public class Exit implements UI_Menu {
    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
