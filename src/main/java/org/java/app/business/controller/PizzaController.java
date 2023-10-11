package org.java.app.business.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String name) {
		
		List<Pizza> pizzas = name == null ?
							 pizzaService.findAll():
							 pizzaService.findByName(name);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("name", name);
		
		return "pizza/pizza-index";
	}
	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza/pizza-show";
	}
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza/pizza-create";
	}
	
	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		return savePizza(pizza, bindingResult, model, ra, true);		
	}
	
	@PostMapping("/update/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		return savePizza(pizza, bindingResult, model, ra, false);		
	}

	
	@GetMapping("/update/{id}")
	public String getBookUpdate(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		pizza.setPrice((pizza.getPrice() / 10000));
		model.addAttribute("pizza", pizza);
		
		return "pizza/pizza-create";
	}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id, RedirectAttributes ra) {
		Pizza pizza = pizzaService.findById(id);
		ra.addFlashAttribute("deleteMessage", "Pizza con ID: " + pizza.getId() + " (" + pizza.getName() + ") cancellata");
		pizzaService.delete(pizza);
		return "redirect:/pizzas";
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
			Model model, @PathVariable("pizza_id") int pizzaId) {
		
		return saveOffer(specialOffer, bindingResult, model, pizzaId);

	}

	
	@PostMapping("/{pizza_id}/addOffer")
	public String storeBorrowing(
			@Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult,			
			@PathVariable("pizza_id") int id, Model model) {
		
		return saveOffer(specialOffer, bindingResult, model, id);
	}
	
	
	private String savePizza(Pizza pizza, BindingResult bindingResult, Model model, 
			RedirectAttributes ra, boolean isNew) {
		if (bindingResult.hasErrors()) {
			pizza.setPrice(pizza.getPrice() / 10000); //In caso di errore invia dato corretto indietro (vedere return*100)
			return "pizza/pizza-create";
		}
		else {
			ra.addFlashAttribute("updateMessage", "Pizza " + (isNew ? 
															"creata " :
															"modificata ")
														+ "correttamente!");
			pizzaService.save(pizza);
			return "redirect:/pizzas/" + pizza.getId();

		}
		
		
	}
	
	private String saveOffer(SpecialOffer specialOffer, BindingResult bindingResult, Model model, int pizzaId) {
		Pizza pizza = pizzaService.findById(pizzaId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("pizza", pizza); //per titolo pagina
			return "offer/offer-create";
		}
		else {
		
		specialOffer.setPizza(pizza);
		specialOfferService.save(specialOffer);
		
		return "redirect:/pizzas/" + pizzaId;
		}
	}}
	

