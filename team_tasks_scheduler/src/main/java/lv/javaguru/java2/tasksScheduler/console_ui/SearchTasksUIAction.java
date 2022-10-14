package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.SearchTasksService;

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
        System.out.println("Enter order criterion (description/due date/end date):");
        String orderBy = scanner.nextLine();
        System.out.println("Enter ordering direction (ascending/descending):");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);
        System.out.println("Enter page size: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());

        //at first request we get found task count and check for request errors
        SearchTasksRequest request = new SearchTasksRequest(searchPhrase, ordering);
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
            int foundTaskCount = response.getTasks().size();
            System.out.println("Found task count:" + foundTaskCount);

            if (pageSize > foundTaskCount)
                pageSize = foundTaskCount;

            int numberOfPages = foundTaskCount/pageSize;
            int numberOfPagesRemainder = foundTaskCount%pageSize;
            int pageNumber;
            //get whole page content
            for (pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
                Paging paging = new Paging(pageNumber, pageSize);
                request = new SearchTasksRequest(searchPhrase, ordering, paging);
                response = searchTasksService.execute(request);
                //System.out.println("Tasks found:");
                response.getTasks().forEach(System.out::println);
                System.out.println("Show next page? (Y/N)");
                String nextPage = scanner.nextLine();
                if (nextPage.toLowerCase().equals("n")) {
                    return true;
                }
            }
            //get content which did no fill whole page
            if (numberOfPagesRemainder > 0) {
                Paging paging = new Paging(pageNumber, pageSize);
                request = new SearchTasksRequest(searchPhrase, ordering, paging);
                response = searchTasksService.execute(request);
                //System.out.println("Tasks found:");
                response.getTasks().forEach(System.out::println);
            }
            return true;
        }
    }
}
