package lv.javaguru.java2.rentapp.core.requests;

import lombok.Getter;

@Getter
public class Ordering {

	private String orderBy;
	private String orderDirection;

	public Ordering(String orderBy, String orderDirection) {
		this.orderBy = orderBy;
		this.orderDirection = orderDirection;
	}
}
