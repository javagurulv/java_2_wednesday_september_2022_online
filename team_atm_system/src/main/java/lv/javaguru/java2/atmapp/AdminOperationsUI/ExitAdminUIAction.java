package lv.javaguru.java2.atmapp.AdminOperationsUI;

public class ExitAdminUIAction implements AdminUIactions {


    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
