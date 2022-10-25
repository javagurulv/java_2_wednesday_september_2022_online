package lv.javaguru.java2.library.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import lv.javaguru.java2.library.core.services.GetAllBooksService;

@Controller
public class ShowAllBooksController {

	@Autowired private GetAllBooksService getAllBooksService;


	@GetMapping(value = "/showAllBooks")
	public String showAllBooks(ModelMap modelMap) {
		GetAllBooksResponse response = getAllBooksService.execute(
				new GetAllBooksRequest()
		);
		modelMap.addAttribute("books", response.getBooks());
		return "/showAllBooks";
	}

}
