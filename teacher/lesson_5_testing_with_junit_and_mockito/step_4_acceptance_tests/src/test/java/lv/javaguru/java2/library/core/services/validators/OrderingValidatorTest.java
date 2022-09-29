package lv.javaguru.java2.library.core.services.validators;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.responses.CoreError;

public class OrderingValidatorTest {

	private OrderingValidator validator = new OrderingValidator();

	@Test
	public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
		Ordering ordering = new Ordering("title", null);
		List<CoreError> errors = validator.validate(ordering);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderDirection");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenOrderByAreEmpty() {
		Ordering ordering = new Ordering(null, "ASCENDING");
		List<CoreError> errors = validator.validate(ordering);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenOrderByContainNotValidValue() {
		Ordering ordering = new Ordering("notValidValue", "ASCENDING");
		List<CoreError> errors = validator.validate(ordering);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Must contain 'author' or 'title' only!");
	}

	@Test
	public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
		Ordering ordering = new Ordering("title", "notValidValue");
		List<CoreError> errors = validator.validate(ordering);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderDirection");
		assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
	}

}