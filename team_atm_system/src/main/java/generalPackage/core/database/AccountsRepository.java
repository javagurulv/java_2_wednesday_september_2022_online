package generalPackage.core.database;

import generalPackage.core.domain.Accounts;

import java.util.List;

public interface AccountsRepository {

    void addAccount(Accounts accounts);

    boolean deleteAccount(int userID);

    List<Accounts> getAllAccounts();

    //    added:
    boolean increaseBalance(int userID, int amount);

    boolean decreaseBalance(int userID, int amount);

    int printBalance(int userID);

    boolean isExist(String name);

    // added 23-09:
    Accounts findUserByID(int userID);

    List <Accounts> searchAccountByName (String name);


}
