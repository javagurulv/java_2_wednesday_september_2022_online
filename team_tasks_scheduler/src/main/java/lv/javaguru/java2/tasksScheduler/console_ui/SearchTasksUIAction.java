package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.SearchTasksService;

import java.util.List;
import java.util.Scanner;

public class SearchTasksUIAction implements UIAction {
    Scanner scanner = new Scanner(System.in);
    private SearchTasksService searchTasksService;

    public SearchTasksUIAction(SearchTasksService searchTasksService) {
        this.searchTasksService = searchTasksService;
    }

    @Override
    public boolean execute() {
        System.out.println("Please enter search phrase:");
        String searchPhrase = scanner.nextLine();

        SearchTasksRequest request = new SearchTasksRequest(searchPhrase);
        SearchTasksResponse response = searchTasksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            List<Task> tasks = response.getTasks();
            if (tasks == null || tasks.isEmpty()) {
                System.out.println("There are no tasks found");
                return true;
            }

            return true;
        }
    }
}
