package org.java.app.business.controller;

import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.pojo.SpecialOffer;
import org.java.app.business.db.serv.PizzaService;
import org.java.app.business.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class SpecialOfferController {

	@Autowired
	private SpecialOfferService specialOfferService;
	@Autowired
	private PizzaService pizzaService;
	
	private String saveOffer(SpecialOffer specialOffer, BindingResult bindingResult, Model model,
			int pizzaId, RedirectAttributes ra, boolean isNew) {
		Pizza pizza = pizzaService.findById(pizzaId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("pizza", pizza); //per titolo pagina
			return "offer/offer-create";
		}
		else {
			ra.addFlashAttribute("updateMessage", "Offerta speciale " + (isNew ? 
					"creata " :
					"modificata ")
				+ "correttamente!");
		
		specialOffer.setPizza(pizza);
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizzas/" + pizzaId;
		}
	}
	
	@GetMapping("/{id}/addOffer")
	public String getOfferCreateForm(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		model.addAttribute("specialOffer", new SpecialOffer());
		
		return "offer/offer-create";
	}
	
	@GetMapping("/{pizza_id}/editOffer/{id}")
	public String getOfferEditForm(@PathVariable int id, @PathVariable("pizza_id") int pizzaId,
			Model model) {
		Pizza pizza = pizzaService.findById(pizzaId);
		SpecialOffer specialOffer = specialOfferService.findById(id);
		model.addAttribute("pizza", pizza);
		model.addAttribute("specialOffer", specialOffer);
		
		return "offer/offer-create";
	}
	
	@PostMapping("/{pizza_id}/editOffer/{id}")
	public String updateOffer(@Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult,
			Model model, RedirectAttributes ra, @PathVariable("pizza_id") int pizzaId) {
		
		return saveOffer(specialOffer, bindingResult, model, pizzaId, ra, false);

	}

	
	@PostMapping("/{pizza_id}/addOffer")
	public String storeBorrowing(
			@Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult,			
			@PathVariable("pizza_id") int id, Model model, RedirectAttributes ra) {
		
		return saveOffer(specialOffer, bindingResult, model, id, ra, true);
	}
}
