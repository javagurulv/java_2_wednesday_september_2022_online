package lv.javaguru.java2.eBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexHtmlController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
