package atmApplication.Database;

import atmApplication.Accounts;
import java.util.List;

public interface Database {

    void addAccount(Accounts accounts);

    void deleteAccount(int userID);

    List<Accounts> getAllAccounts();

    //    added:
    void increaseBalance(int userID, int amount);

    void decreaseBalance (int userID, int amount);

    void printBalance (int userID);

    boolean isExist (String name);

}

