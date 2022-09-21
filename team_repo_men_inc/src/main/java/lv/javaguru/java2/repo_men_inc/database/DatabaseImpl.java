package lv.javaguru.java2.repo_men_inc.database;

import lv.javaguru.java2.repo_men_inc.Debtor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl implements Database{

    private Long id = 1L;
    private final List<Debtor> debtors = new ArrayList<>();

    @Override
    public void save(Debtor debtor) {
        debtor.setId(id);
        debtors.add(debtor);
        id++;
    }

    @Override
    public void delete(Long id) {
        debtors.stream()
                .filter(debtor -> debtor.getId().equals(id))
                .findFirst()
                .ifPresent(debtors::remove);
    }

    @Override
    public Debtor getById(Long id) {
        return debtors.stream().filter(debtor -> debtor.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Debtor> getAllDebtors() {
        return debtors;
    }
}
