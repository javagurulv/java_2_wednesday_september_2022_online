package lv.javaguru.java2.atmapp.AdminOperationsUI;

import lv.javaguru.java2.atmapp.AdminOperations.GetAllAccountsService;

public class GetAllAccountsAdminUIAction implements AdminUIactions {

private GetAllAccountsService getAllAccountsService;

    public GetAllAccountsAdminUIAction(GetAllAccountsService getAllAccountsService) {
        this.getAllAccountsService = getAllAccountsService;
    }


    @Override
    public void execute() {
        getAllAccountsService.execute().forEach(System.out::println);
    }
}
