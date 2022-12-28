package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.SearchDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.SearchDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchDebtorService {

    @Autowired
    private Database database;
    @Autowired
    private SearchDebtorValidator searchDebtorValidator;

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    public SearchDebtorResponse execute(SearchDebtorRequest searchDebtorRequest) {
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        if (!errors.isEmpty()) {
            return new SearchDebtorResponse(errors, true);
        }

        List<Debtor> debtors = search(searchDebtorRequest);
        debtors = order(debtors, searchDebtorRequest.getOrdering());
        debtors = paging(debtors, searchDebtorRequest.getPaging());
        return new SearchDebtorResponse(debtors);
    }

    private List<Debtor> search(SearchDebtorRequest searchDebtorRequest) {
        List<Debtor> debtors = new ArrayList<>();

        if (searchDebtorRequest.isNameProvided() && searchDebtorRequest.isListItemProvided()) {
            debtors = Collections.singletonList(database.getByNameAndListItem(searchDebtorRequest.getName(), searchDebtorRequest.getListItem()));
        }

        if (searchDebtorRequest.isNameProvided() && !searchDebtorRequest.isListItemProvided()) {
            debtors = Collections.singletonList(database.getByName(searchDebtorRequest.getName()));
        }

        if (!searchDebtorRequest.isNameProvided() && searchDebtorRequest.isListItemProvided()) {
            debtors = database.getByListItem(searchDebtorRequest.getListItem());
        }

        return debtors;
    }

    private List<Debtor> order(List<Debtor> debtors, Ordering ordering) {
        if (orderingEnabled && ordering != null) {
            Comparator<Debtor> debtorComparator = null;
            switch (ordering.getOrderBy()) {
                case NAME -> debtorComparator = Comparator.comparing(Debtor::getName);
            }
            if (debtorComparator != null) {
                if (ordering.getOrderDirection().getDirection().equals(OrderingDirection.DESC.getDirection())) {
                    debtorComparator = debtorComparator.reversed();
                }
                return debtors.stream().sorted(debtorComparator).collect(Collectors.toList());
            }
        }
        return debtors;
    }

    private List<Debtor> paging(List<Debtor> debtors, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return debtors.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return debtors;
        }
    }
}
