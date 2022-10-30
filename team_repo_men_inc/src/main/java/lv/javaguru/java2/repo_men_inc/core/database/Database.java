package lv.javaguru.java2.repo_men_inc.core.database;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;

import java.util.List;

public interface Database {
    boolean save(Debtor debtor);
    boolean deleteById(Long id);
    Debtor getById(Long id);
    Debtor getByName(String name);
    Debtor getByNameAndListItem(String name, String listItem);
    List<Debtor> getByListItem(String listItem);
    List<Debtor> getAllDebtors();
}
