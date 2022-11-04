package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.validators.SearchBankAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class SearchBankAccountService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private SearchBankAccountValidator validator;

    public SearchBankAccountResponse execute(SearchBankAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchBankAccountResponse(errors, null);
        } else {
            List<BankAccount> bankAccounts = search(request);
            bankAccounts = ordering(bankAccounts, request.getOrder());
            if (request.getPaging() != null) {
                bankAccounts = paging(bankAccounts, request.getPaging());
            }
            return new SearchBankAccountResponse(null, bankAccounts);
        }
    }

    private List<BankAccount> search(SearchBankAccountRequest request) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        if (request.nameNullCheck() && !request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findByName(request.getName());
        }
        if (!request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findBySurname(request.getSurname());
        }
        if (!request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findByPersonalCode(request.getPersonalCode());
        }
        if (request.nameNullCheck() && request.surnameNullCheck() && !request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findByNameAndSurname(request.getName(), request.getSurname());
        }
        if (request.nameNullCheck() && !request.surnameNullCheck() && request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findByNameAndPersonalCode(request.getName(), request.getPersonalCode());
        }
        if (!request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findBySurnameAndPersonalCode(request.getSurname(), request.getPersonalCode());
        }
        if (request.nameNullCheck() && request.surnameNullCheck() && request.personalCodeNullCheck()) {
            bankAccounts = bankAccountRepository.findByNameAndSurnameAndPersonalCode(request.getName(), request.getSurname(),
                    request.getPersonalCode());
        }
        return bankAccounts;
    }


    private List<BankAccount> ordering(List<BankAccount> bankAccounts, Ordering order) {
        if (order != null) {
            Comparator<BankAccount> comparator = null;
            switch (order.getOrderBy()) {
                case "name" -> comparator = Comparator.comparing(BankAccount::getName);
                case "surname" -> comparator = Comparator.comparing(BankAccount::getSurname);
                case "personal code" -> comparator = Comparator.comparing(BankAccount::getPersonalCode);
            }
            if (comparator != null) {
                if (order.getOrderDirection().equals("DESCENDING")) {
                    comparator = comparator.reversed();
                }
                return bankAccounts.stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
            }
        }
        return bankAccounts;
    }

    private List<BankAccount> paging(List<BankAccount> bankAccounts, Paging paging) {
        if (paging == null) {
            return bankAccounts;
        } else {
            return bankAccounts.stream()
                    .skip((long) (paging.getPageNumber() - 1) * paging.getPageSize())
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        }
    }
}