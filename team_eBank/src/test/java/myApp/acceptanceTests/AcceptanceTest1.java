package myApp.acceptanceTests;

import myApp.config.SpringCoreConfiguration;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import myApp.core.services.AddBankAccountService;
import myApp.core.services.GetAllBankAccountsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest1 {

    @Autowired
    private AddBankAccountService addService;
    @Autowired
    private GetAllBankAccountsService getAllBankAccountsServiceService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Before
    public void setup() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("bank_accounts");
        tableNames.add("users");

        for (String tableName : tableNames) {
            String sql = "delete from " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

        @Test
        public void testShouldReturnCorrectBankAccountList () {
            AddBankAccountRequest bankAccountOne = new AddBankAccountRequest("Example", "ExampleTwo",
                    "000000-00001");
            addService.execute(bankAccountOne);
            AddBankAccountRequest bankAccountTwo = new AddBankAccountRequest("Example", "",
                    "000000-00001");
            addService.execute(bankAccountTwo);
            GetAllBankAccountsResponse response = getAllBankAccountsServiceService.execute(new GetAllBankAccountsRequest());
            assertEquals(1, response.getBankAccounts().size());
        }
    }