package lv.javaguru.java2.repo_men_inc.acceptance_tests;

import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.dependency_injection.ApplicationContext;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIApplicationContextBuilder;

public class AcceptanceTestBase {
    protected ApplicationContext appContext =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.repo_men_inc");

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
