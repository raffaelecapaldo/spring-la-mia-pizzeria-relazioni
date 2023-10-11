package org.java.app.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	@GetMapping
	public String homeToPizza() {
		return "redirect:/pizzas";
		
	}
}
