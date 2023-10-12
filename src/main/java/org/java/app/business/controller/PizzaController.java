package org.java.app.business.controller;

import java.util.List;

import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.serv.IngredientService;
import org.java.app.business.db.serv.PizzaService;
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
	private IngredientService ingredientService;

	
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
		
		model.addAttribute("ingredients", ingredientService.findAll());
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
	public String getPizzaUpdate(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		pizza.setPrice((pizza.getPrice() / 10000));
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredientService.findAll());

		
		return "pizza/pizza-create";
	}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id, RedirectAttributes ra) {
		Pizza pizza = pizzaService.findById(id);
		//Cancello le offerte di questa pizza per evitare errori nel DB
		pizzaService.deleteAllOffers(pizza);
		ra.addFlashAttribute("deleteMessage", "Pizza con ID: " + pizza.getId() + " (" + pizza.getName() + ") cancellata");
		pizzaService.delete(pizza);
		return "redirect:/pizzas";
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
		
		
	}}
	

