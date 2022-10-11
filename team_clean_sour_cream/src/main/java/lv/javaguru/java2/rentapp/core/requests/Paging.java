package lv.javaguru.java2.rentapp.core.requests;

import lombok.Getter;

@Getter
public class Paging {

	private Integer pageNumber;
	private Integer pageSize;

	public Paging(Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

}
