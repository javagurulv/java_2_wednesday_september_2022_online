package lv.javaguru.java2.repo_men_inc.database;

import lv.javaguru.java2.repo_men_inc.Debtor;

import java.util.List;

public interface Database {
    boolean save(Debtor debtor);
    boolean deleteById(Long id);
    Debtor getById(Long id);
    List<Debtor> getAllDebtors();
}
