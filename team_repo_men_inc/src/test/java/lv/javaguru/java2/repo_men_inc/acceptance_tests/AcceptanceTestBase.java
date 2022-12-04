package lv.javaguru.java2.repo_men_inc.acceptance_tests;

import lv.javaguru.java2.repo_men_inc.DatabaseCleaner;
import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.database.JdbcDatabaseImpl;
import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.core.services.SearchDebtorService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigInteger;

public class AcceptanceTestBase {
    public ApplicationContext appContext = new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);

    @Before
    public void setup() {
        getDatabaseCleaner().clean();

        Database database = appContext.getBean(JdbcDatabaseImpl.class);

        // create some debtors
        database.saveDebtorAndReturnId(1L,"mr x");
        database.saveDebtorAndReturnId(2L, "mr y");
        database.saveDebtorAndReturnId(3L, "mr z");

        // create some items
        BigInteger itemLegId = database.saveItem("leg");
        BigInteger itemArmId = database.saveItem("arm");
        BigInteger itemLiverId = database.saveItem("liver");

        // add items to debtors lists
        database.updateList(itemLegId, 1L);
        database.updateList(itemLegId, 2L);
        database.updateList(itemArmId, 2L);
        database.updateList(itemLiverId, 3L);

        ReflectionTestUtils.setField(appContext.getBean(SearchDebtorService.class), "orderingEnabled", true);
        ReflectionTestUtils.setField(appContext.getBean(SearchDebtorService.class), "pagingEnabled", true);

    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

    long idOfTheFirstDebtorInTheDatabase = 1L;
    long nonExistentDebtorsId = 10L;

    String debtorPresentInDatabase = "mr x";
    String debtorNotPresentInDatabase = "mr T";
    String itemPresentInSeveralDebtorsLists = "leg";
    String itemNotPresentInAnyDebtorsLists = "eye";

    String itemNotYetPresentInTheItemsListOfTheFirstDebtorInTheDatabase = "heart";
    String itemAlreadyPresentInTheItemsListOfTheFirstDebtorInTheDatabase = "leg";

    Ordering orderByNameDescending = new Ordering(OrderingType.NAME, OrderingDirection.DESC);
    Ordering orderByListSizeAscending = new Ordering(OrderingType.LIST_ITEM_SIZE, OrderingDirection.ASC);

    Paging firstPage = new Paging(1, 1);
    Paging secondPage = new Paging(2, 1);
}
