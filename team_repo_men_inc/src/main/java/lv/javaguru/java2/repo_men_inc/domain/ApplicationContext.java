package lv.javaguru.java2.repo_men_inc.domain;

import lv.javaguru.java2.repo_men_inc.console_ui.*;
import lv.javaguru.java2.repo_men_inc.core.validators.*;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import lv.javaguru.java2.repo_men_inc.services.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new DatabaseImpl());

        beans.put(AddDebtorValidator.class, new AddDebtorValidator(getBean(Database.class)));
        beans.put(AddDebtorService.class, new AddDebtorService(getBean(Database.class), getBean(AddDebtorValidator.class)));
        beans.put(AddDebtorUIAction.class, new AddDebtorUIAction(getBean(AddDebtorService.class)));

        beans.put(RemoveDebtorValidator.class, new RemoveDebtorValidator(getBean(Database.class)));
        beans.put(RemoveDebtorService.class, new RemoveDebtorService(getBean(Database.class), getBean(RemoveDebtorValidator.class)));
        beans.put(RemoveDebtorUIAction.class, new RemoveDebtorUIAction(getBean(RemoveDebtorService.class)));

        beans.put(PrintDebtorListService.class, new PrintDebtorListService(getBean(Database.class)));
        beans.put(PrintDebtorListUIAction.class, new PrintDebtorListUIAction(getBean(PrintDebtorListService.class)));

        beans.put(AddHarvestedItemValidator.class, new AddHarvestedItemValidator(getBean(Database.class)));
        beans.put(AddHarvestedItemService.class, new AddHarvestedItemService(getBean(Database.class), getBean(AddHarvestedItemValidator.class)));
        beans.put(AddHarvestedItemUIAction.class, new AddHarvestedItemUIAction(getBean(AddHarvestedItemService.class)));

        beans.put(SearchDebtorFieldValidator.class, new SearchDebtorFieldValidator());
        beans.put(OrderingValidator.class, new OrderingValidator());
        beans.put(PagingValidator.class, new PagingValidator());
        beans.put(SearchDebtorValidator.class, new SearchDebtorValidator(getBean(PagingValidator.class), getBean(OrderingValidator.class), getBean(SearchDebtorFieldValidator.class)));
        beans.put(SearchDebtorService.class, new SearchDebtorService(getBean(Database.class), getBean(SearchDebtorValidator.class)));
        beans.put(SearchDebtorUIAction.class, new SearchDebtorUIAction(getBean(SearchDebtorService.class)));

        beans.put(ExitUIAction.class, new ExitUIAction());

        Database databaseImpl = getBean(Database.class);
        databaseImpl.save(new Debtor("mr x"));
        databaseImpl.save(new Debtor("mr y"));
        databaseImpl.save(new Debtor("mr z"));
        databaseImpl.getById(1L).getList().add("leg");
        databaseImpl.getById(2L).getList().add("arm");
        databaseImpl.getById(3L).getList().add("arm");
        databaseImpl.getById(3L).getList().add("liver");
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
