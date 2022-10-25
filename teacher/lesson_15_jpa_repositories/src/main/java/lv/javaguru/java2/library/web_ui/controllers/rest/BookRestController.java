package lv.javaguru.java2.library.web_ui.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.requests.DeleteBookRequest;
import lv.javaguru.java2.library.core.requests.GetBookRequest;
import lv.javaguru.java2.library.core.requests.UpdateBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.DeleteBookResponse;
import lv.javaguru.java2.library.core.responses.GetBookResponse;
import lv.javaguru.java2.library.core.responses.UpdateBookResponse;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.DeleteBookService;
import lv.javaguru.java2.library.core.services.GetBookService;
import lv.javaguru.java2.library.core.services.UpdateBookService;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired private GetBookService getBookService;
	@Autowired private AddBookService addBookService;
	@Autowired private UpdateBookService updateBookService;
	@Autowired private DeleteBookService deleteBookService;

	@GetMapping(path = "/{id}", produces = "application/json")
	public GetBookResponse getBook(@PathVariable Long id) {
		GetBookRequest request = new GetBookRequest(id);
		return getBookService.execute(request);
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public AddBookResponse addBook(@RequestBody AddBookRequest request) {
		return addBookService.execute(request);
	}

	@PutMapping(path = "/{id}",
			consumes = "application/json",
			produces = "application/json")
	public UpdateBookResponse updateBook(@RequestBody UpdateBookRequest request) {
		return updateBookService.execute(request);
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	public DeleteBookResponse deleteBook(@PathVariable Long id) {
		DeleteBookRequest request = new DeleteBookRequest(id);
		return deleteBookService.execute(request);
	}


/*
	@DeleteMapping(path = "/deleteByAuthor",
			consumes = "application/json",
			produces = "application/json")
	public DeleteBookResponse deleteBooksByAuthor(@RequestBody DeleteBookByAuthorRequest request) {
		return deleteBookNByAuthorService.execute(request);
	}
*/

}