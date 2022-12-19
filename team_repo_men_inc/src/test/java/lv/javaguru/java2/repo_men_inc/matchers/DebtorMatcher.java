package lv.javaguru.java2.repo_men_inc.matchers;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.mockito.ArgumentMatcher;

public class DebtorMatcher implements ArgumentMatcher<Debtor> {

    private final String name;

    public DebtorMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Debtor debtor) {
        return debtor.getName().equals(this.name);
    }
}
