package lv.javaguru.java2.repo_men_inc.console_ui;

public class ExitUIAction implements UIAction{

    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
