package org.java.app.business.controller;

import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.pojo.SpecialOffer;
import org.java.app.business.db.serv.PizzaService;
import org.java.app.business.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {
	
	@Autowired
	private SpecialOfferService specialOfferService;

	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("specialOffer", new SpecialOffer());
		
		return "offer/offer-create";
	}
}
