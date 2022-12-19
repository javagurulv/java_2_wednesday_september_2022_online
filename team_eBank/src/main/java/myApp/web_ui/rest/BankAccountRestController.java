package myApp.web_ui.rest;
/*
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.GetAllBankAccountsService;
import myApp.core.services.RemoveBankAccountService;
import myApp.core.services.SearchBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountRestController {

    @Autowired
    private GetAllBankAccountsService getBookService;
    @Autowired
    private AddBankAccountService addBookService;
    @Autowired
    private RemoveBankAccountService deleteBookService;
    @Autowired
    private SearchBankAccountService searchBookService;


    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchBankAccountResponse searchBankAccountPost(@RequestBody SearchBankAccountRequest request) {
        return searchBookService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchBankAccountResponse searchBankAccountGet(@RequestParam String name,
                                              @RequestParam String surname, @RequestParam String personalCode) {
        SearchBankAccountRequest request = new SearchBankAccountRequest(name, surname, personalCode);
        return searchBookService.execute(request);
    }

    @GetMapping(path = "/{personalCode}", produces = "application/json")
    public GetAllBankAccountsResponse getBankAccounts(@PathVariable String personalCode) {
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest(personalCode);
        return getBookService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddBankAccountResponse addBankAccount(@RequestBody AddBankAccountRequest request) {
        return addBookService.execute(request);
    }


    @DeleteMapping(path = "/{personalCode}", produces = "application/json")
    public RemoveBankAccountResponse deleteBankAccount(@PathVariable String personalCode) {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(personalCode);
        return deleteBookService.execute(request);
    }
}


 */