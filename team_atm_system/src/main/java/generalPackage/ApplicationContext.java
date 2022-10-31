package generalPackage;

import generalPackage.adminOperationsUI.*;
import generalPackage.core.database.AccountDatabaseImpl;
import generalPackage.core.database.Database;
import generalPackage.core.services.adminOperations.AddAccountService;
import generalPackage.core.services.adminOperations.DeleteAccountService;
import generalPackage.core.services.adminOperations.FindUserByIDService;
import generalPackage.core.services.adminOperations.GetAllAccountsService;
import generalPackage.core.services.adminOperations.adminValidators.AddAccountServiceValidator;
import generalPackage.core.services.adminOperations.adminValidators.DeleteAccountServiceValidator;
import generalPackage.core.services.adminOperations.adminValidators.FindUserByIDServiceValidator;
import generalPackage.core.services.usersOperations.DecreaseBalance;
import generalPackage.core.services.usersOperations.IncreaseBalance;
import generalPackage.core.services.usersOperations.PrintBalance;
import generalPackage.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;
import generalPackage.core.services.usersOperations.usersValidators.IncreaseBalanceValidator;
import generalPackage.core.services.usersOperations.usersValidators.PrintBalanceValidator;
import generalPackage.usersOperationsUI.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {

        beans.put(Database.class, new AccountDatabaseImpl());

//        Admin part:

        beans.put(AddAccountServiceValidator.class, new AddAccountServiceValidator());
        beans.put(DeleteAccountServiceValidator.class, new DeleteAccountServiceValidator());
        beans.put(FindUserByIDServiceValidator.class, new FindUserByIDServiceValidator());

        beans.put(AddAccountService.class, new AddAccountService(
                getBean(Database.class),
                getBean(AddAccountServiceValidator.class)));

        beans.put(DeleteAccountService.class, new DeleteAccountService(
                getBean(Database.class),
                getBean(DeleteAccountServiceValidator.class)));

        beans.put(FindUserByIDService.class, new FindUserByIDService(
                getBean(Database.class),
                getBean(FindUserByIDServiceValidator.class)));

        beans.put(GetAllAccountsService.class, new GetAllAccountsService(
                getBean(Database.class)));

        beans.put(AddAccountAdminUIAction.class, new AddAccountAdminUIAction(
                getBean(AddAccountService.class)));

        beans.put(DeleteAccountAdminUIAction.class, new DeleteAccountAdminUIAction(
                getBean(DeleteAccountService.class)));

        beans.put(FindUserAdminUIAction.class, new FindUserAdminUIAction(
                getBean(FindUserByIDService.class)));

        beans.put(GetAllAccountsAdminUIAction.class, new GetAllAccountsAdminUIAction(
                getBean(GetAllAccountsService.class)));

        beans.put(ExitAdminUIAction.class, new ExitAdminUIAction());

//        Users part:

        beans.put(DecreaseBalanceValidator.class, new DecreaseBalanceValidator());
        beans.put(IncreaseBalanceValidator.class, new IncreaseBalanceValidator());
        beans.put(PrintBalanceValidator.class, new PrintBalanceValidator());

        beans.put(DecreaseBalance.class, new DecreaseBalance(
                getBean(Database.class),
                getBean(DecreaseBalanceValidator.class)));

        beans.put(IncreaseBalance.class, new IncreaseBalance(
                getBean(Database.class),
                getBean(IncreaseBalanceValidator.class)));

        beans.put(PrintBalance.class, new PrintBalance(
                getBean(Database.class),
                getBean(PrintBalanceValidator.class)));

        beans.put(DecreaseBalanceServiceUIAction.class, new DecreaseBalanceServiceUIAction(
                getBean(DecreaseBalance.class)));

        beans.put(IncreaseBalanceServiceUIAction.class, new IncreaseBalanceServiceUIAction(
                getBean(IncreaseBalance.class)));

        beans.put(PrintBalanceServiceUIAction.class, new PrintBalanceServiceUIAction(
                getBean(PrintBalance.class)));

        beans.put(MainMenuUIAction.class, new MainMenuUIAction());

        beans.put(ExitServiceUIAction.class, new ExitServiceUIAction());

    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }


//    public <T extends Object> T getBean(Class c) {
//        return (T) beans.get(c);
//    }
}
