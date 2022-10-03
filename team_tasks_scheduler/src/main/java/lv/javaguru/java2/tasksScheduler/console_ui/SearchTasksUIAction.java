package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.SearchTasksService;

public class SearchTasksUIAction implements UIAction {
    private SearchTasksService searchTasksService;

    public SearchTasksUIAction(SearchTasksService searchTasksService) {
        this.searchTasksService = searchTasksService;
    }

    @Override
    public boolean execute() {


        return true;
    }
}
