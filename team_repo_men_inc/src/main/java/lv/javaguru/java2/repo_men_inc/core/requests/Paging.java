package lv.javaguru.java2.repo_men_inc.core.requests;

public class Paging {

	private final Integer pageNumber;
	private final Integer pageSize;

	public Paging(Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}
}
