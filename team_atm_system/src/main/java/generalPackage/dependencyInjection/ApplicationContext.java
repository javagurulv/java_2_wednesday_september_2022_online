package generalPackage.dependencyInjection;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {

//        beans.put(Database.class, new AccountDatabaseImpl());

//        Admin part:

//        beans.put(AddAccountServiceValidator.class, new AddAccountServiceValidator());
//        beans.put(DeleteAccountServiceValidator.class, new DeleteAccountServiceValidator());
//        beans.put(FindUserByIDServiceValidator.class, new FindUserByIDServiceValidator());
//        beans.put(OrderingValidator.class, new OrderingValidator());
//        beans.put(GetAllAccountsServiceValidator.class, new GetAllAccountsServiceValidator(
//                getBean(OrderingValidator.class)));
//        beans.put(SearchAccountsServiceValidator.class, new SearchAccountsServiceValidator());

//        beans.put(AddAccountService.class, new AddAccountService(
//                getBean(Database.class),
//                getBean(AddAccountServiceValidator.class)));
//
//        beans.put(DeleteAccountService.class, new DeleteAccountService(
//                getBean(Database.class),
//                getBean(DeleteAccountServiceValidator.class)));
//
//        beans.put(FindUserByIDService.class, new FindUserByIDService(
//                getBean(Database.class),
//                getBean(FindUserByIDServiceValidator.class)));
//
//        beans.put(GetAllAccountsService.class, new GetAllAccountsService(
//                getBean(Database.class),
//                getBean(GetAllAccountsServiceValidator.class)));
//
//        beans.put(SearchAccountsService.class, new SearchAccountsService(
//                getBean(Database.class),
//                getBean(SearchAccountsServiceValidator.class)));

//        beans.put(AddAccountAdminUIAction.class, new AddAccountAdminUIAction(
//                getBean(AddAccountService.class)));
//
//        beans.put(DeleteAccountAdminUIAction.class, new DeleteAccountAdminUIAction(
//                getBean(DeleteAccountService.class)));
//
//        beans.put(FindUserAdminUIAction.class, new FindUserAdminUIAction(
//                getBean(FindUserByIDService.class)));
//
//        beans.put(GetAllAccountsAdminUIAction.class, new GetAllAccountsAdminUIAction(
//                getBean(GetAllAccountsService.class)));
//
//        beans.put(SearchAccountsAdminUIAction.class, new SearchAccountsAdminUIAction(
//                getBean(SearchAccountsService.class)));
//
//        beans.put(ExitAdminUIAction.class, new ExitAdminUIAction());

//        Users part:

//        beans.put(DecreaseBalanceValidator.class, new DecreaseBalanceValidator());
//        beans.put(IncreaseBalanceValidator.class, new IncreaseBalanceValidator());
//        beans.put(PrintBalanceValidator.class, new PrintBalanceValidator());

//        beans.put(DecreaseBalance.class, new DecreaseBalance(
//                getBean(Database.class),
//                getBean(DecreaseBalanceValidator.class)));
//
//        beans.put(IncreaseBalance.class, new IncreaseBalance(
//                getBean(Database.class),
//                getBean(IncreaseBalanceValidator.class)));
//
//        beans.put(PrintBalance.class, new PrintBalance(
//                getBean(Database.class),
//                getBean(PrintBalanceValidator.class)));

//        beans.put(DecreaseBalanceServiceUIAction.class, new DecreaseBalanceServiceUIAction(
//                getBean(DecreaseBalance.class)));
//
//        beans.put(IncreaseBalanceServiceUIAction.class, new IncreaseBalanceServiceUIAction(
//                getBean(IncreaseBalance.class)));
//
//        beans.put(PrintBalanceServiceUIAction.class, new PrintBalanceServiceUIAction(
//                getBean(PrintBalance.class)));
//
//        beans.put(ExitServiceUIAction.class, new ExitServiceUIAction());

    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }


    public void addBean(Class beanClass, Object beanInstance) {
        beans.put(beanClass, beanInstance);
        Class[] instanceInterfaces = beanClass.getInterfaces();
        for (int i = 0; i < instanceInterfaces.length; i++) {
            Class instanceInterface = instanceInterfaces[i];
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                beans.put(instanceInterface, beanInstance);
            }

        }
    }
}
