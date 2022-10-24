package lv.javaguru.java2.library.web_ui.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.services.AddBookService;

@Controller
public class AddBookController {

	@Autowired private AddBookService addBookService;


	@GetMapping(value = "/addBookToList")
	public String showAddBookPage(ModelMap modelMap,
								  HttpSession session) {
		session.setAttribute("key", 1);
		session.getAttribute("key");

		modelMap.addAttribute("request", new AddBookRequest());
		return "addBookToList";
	}

	@PostMapping("/addBookToList")
	public String processAddBookRequest(@ModelAttribute(value = "request") AddBookRequest request, ModelMap modelMap) {
		AddBookResponse response = addBookService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrors());
			return "addBookToList";
		} else {
			return "redirect:/";
		}
	}

}
