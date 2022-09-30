package lv.javaguru.java2.esimpleforum.console_ui;

import lv.javaguru.java2.esimpleforum.service.PostManagerService;

public class ExitUIAction implements UIAction{

    PostManagerService postManagerService;

    public ExitUIAction(PostManagerService postManagerService) {
        this.postManagerService = postManagerService;
    }

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
