package lv.javaguru.java2.repo_men_inc;

import java.util.List;

public interface Database {
    void save(Debtor debtor);
    void delete(Long id);
    Debtor getById(Long id);
    List<Debtor> getAllDebtors();
}
