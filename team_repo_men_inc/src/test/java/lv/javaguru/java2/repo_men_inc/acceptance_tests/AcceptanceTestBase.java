package lv.javaguru.java2.repo_men_inc.acceptance_tests;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.services.SearchDebtorService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

public class AcceptanceTestBase {
    protected ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);
        appContext.getBean(Database.class).save(new Debtor("mr x"));
        appContext.getBean(Database.class).save(new Debtor("mr y"));
        appContext.getBean(Database.class).save(new Debtor("mr z"));
        appContext.getBean(Database.class).getById(1L).getList().add("leg");
        appContext.getBean(Database.class).getById(2L).getList().add("arm");
        appContext.getBean(Database.class).getById(3L).getList().add("arm");
        appContext.getBean(Database.class).getById(3L).getList().add("liver");

        ReflectionTestUtils.setField(appContext.getBean(SearchDebtorService.class), "orderingEnabled", true);
        ReflectionTestUtils.setField(appContext.getBean(SearchDebtorService.class), "pagingEnabled", true);
    }

    long idOfTheFirstDebtorInTheDatabase = 1L;
    long nonExistentDebtorsId = 10L;

    String debtorPresentInDatabase = "mr x";
    String debtorNotPresentInDatabase = "mr T";
    String itemPresentInSeveralDebtorsLists = "arm";
    String itemNotPresentInAnyDebtorsLists = "eye";

    String itemNotYetPresentInTheItemsListOfTheFirstDebtorInTheDatabase = "heart";
    String itemAlreadyPresentInTheItemsListOfTheFirstDebtorInTheDatabase = "leg";

    Ordering orderByNameDescending = new Ordering(OrderingType.NAME, OrderingDirection.DESC);
    Ordering orderByListSizeAscending = new Ordering(OrderingType.LIST_ITEM_SIZE, OrderingDirection.ASC);

    Paging firstPage = new Paging(1, 1);
    Paging secondPage = new Paging(2, 1);
}
