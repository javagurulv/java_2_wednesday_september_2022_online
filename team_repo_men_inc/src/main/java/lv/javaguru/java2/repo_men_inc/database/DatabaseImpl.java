package lv.javaguru.java2.repo_men_inc.database;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseImpl implements Database{

    private Long id = 1L;
    private final List<Debtor> debtors = new ArrayList<>();

    @Override
    public boolean save(Debtor debtor) {
        debtor.setId(id);
        id++;
        return debtors.add(debtor);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDebtorDeleted = false;
        Optional<Debtor> debtorToBeDeletedOpt = debtors.stream()
                .filter(debtor -> debtor.getId().equals(id))
                .findFirst();
        if (debtorToBeDeletedOpt.isPresent()) {
            Debtor debtorToBeDeleted = debtorToBeDeletedOpt.get();
            isDebtorDeleted = debtors.remove(debtorToBeDeleted);
        }
        return isDebtorDeleted;
    }

    @Override
    public Debtor getById(Long id) {
        return debtors.stream().filter(debtor -> debtor.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Debtor getByName(String name) {
        return debtors.stream().filter(debtor -> debtor.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public List<Debtor> getAllDebtors() {
        return debtors;
    }
}
