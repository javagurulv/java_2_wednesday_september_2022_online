package lv.javaguru.java2.repo_men_inc.database;

import lv.javaguru.java2.repo_men_inc.Debtor;

import java.util.List;

public interface Database {
    void save(Debtor debtor);
    void delete(Long id);
    Debtor getById(Long id);
    List<Debtor> getAllDebtors();
}
