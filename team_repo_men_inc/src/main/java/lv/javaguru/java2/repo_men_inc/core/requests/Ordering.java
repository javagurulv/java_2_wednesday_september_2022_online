package lv.javaguru.java2.repo_men_inc.core.requests;

public class Ordering {

	private final OrderingType orderBy;
	private final OrderingDirection orderDirection;

	public Ordering(OrderingType orderBy, OrderingDirection orderDirection) {
		this.orderBy = orderBy;
		this.orderDirection = orderDirection;
	}

	public OrderingType getOrderBy() {
		return orderBy;
	}

	public OrderingDirection getOrderDirection() {
		return orderDirection;
	}
}
