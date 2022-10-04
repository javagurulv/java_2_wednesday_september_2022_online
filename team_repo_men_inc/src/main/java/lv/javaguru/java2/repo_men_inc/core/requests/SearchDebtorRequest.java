package lv.javaguru.java2.repo_men_inc.core.requests;

public class SearchDebtorRequest {

    private final String name;
    private final String listItem;
    private final Ordering ordering;
    private final Paging paging;

    public SearchDebtorRequest(String name, String listItem, Ordering ordering, Paging paging) {
        this.name = name;
        this.listItem = listItem;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getListItem() {
        return listItem;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isListItemProvided() {
        return this.listItem != null && !this.listItem.isEmpty();
    }

}
