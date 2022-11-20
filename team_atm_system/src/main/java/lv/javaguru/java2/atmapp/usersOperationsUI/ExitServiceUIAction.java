package lv.javaguru.java2.atmapp.usersOperationsUI;

public class ExitServiceUIAction implements UI_Menu {
    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
