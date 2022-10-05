package myApp.core.database;

import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryDatabaseImplTest {

    @Mock
    DataBase dataBase;

    @Test
    public void addBankAccount() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password", Roles.Regular_user
                , "111-000");
        dataBase.addBankAccount(bankAccount);
        verify(dataBase).addBankAccount(bankAccount);

    }

    @Test
    public void deleteBankAccount() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password", Roles.Regular_user
                , "111-000");
        dataBase.deleteBankAccount("111-000");
        verify(dataBase).deleteBankAccount("111-000");
    }

    @Test
    public void bankTransfer() {
        dataBase.bankTransfer("111-000", "222-000", 100);
        verify(dataBase).bankTransfer("111-000", "222-000", 100);
    }

    @Test
    public void openAccount() {
        dataBase.openAccount("111-000");
        verify(dataBase).openAccount("111-000");
    }

    @Test
    public void closeAccount() {
       dataBase.closeAccount("111-000");
       verify(dataBase).closeAccount("111-000");
    }

    @Test
    public void getAllBankAccountsMap() {
        assertNotNull(dataBase.getAllBankAccounts());
    }

    @Test
    public void seeYourAccount() {
        assertNotNull(dataBase.seeYourAccount("111-000"));
    }

    @Test
    public void testFindByName() {
        dataBase.findByName("Example");
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindBySurname() {
        dataBase.findBySurname("Example");
        verify(dataBase).findBySurname("Example");
    }

    @Test
    public void testFindByPersonalCode() {
        dataBase.findByPersonalCode("Example");
        verify(dataBase).findByPersonalCode("Example");
    }

    @Test
    public void testFindByNameAndSurname() {
        dataBase.findByNameAndSurname("Example", "Example");
        verify(dataBase).findByNameAndSurname("Example", "Example");
    }

    @Test
    public void testFindByNameAndPersonalCode() {
        dataBase.findByNameAndPersonalCode("Example", "000-111");
        verify(dataBase).findByNameAndPersonalCode("Example", "000-111");
    }

    @Test
    public void testFindBySurnameAndPersonalCode() {
        dataBase.findBySurnameAndPersonalCode("Example", "000-111");
        verify(dataBase).findBySurnameAndPersonalCode("Example", "000-111");
    }

    @Test
    public void testFindByNameAndSurnameAndPersonalCode() {
        dataBase.findByNameAndSurnameAndPersonalCode("Example", "Example", "000-111");
        verify(dataBase).findByNameAndSurnameAndPersonalCode("Example", "Example", "000-111");
    }
}