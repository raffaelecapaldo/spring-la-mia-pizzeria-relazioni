package org.java.app.business.controller;


import org.java.app.business.db.pojo.Ingredient;
import org.java.app.business.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getIndex(Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());
		
		return "ingredient/ingredient-index";
	}
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "ingredient/ingredient-create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult, Model model, 
			RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			 return "ingredient/ingredient-create";
		}
		else {
			try {
				ingredientService.save(ingredient);
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("nameNotUnique", "Ingrediente con nome già esistente");
				 return "ingredient/ingredient-create";			}
			ra.addFlashAttribute("updateMessage", "Ingrediente aggiunto correttamente");
			return "redirect:/ingredients";
		}
		
	}
	
	@PostMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable int id, RedirectAttributes ra) {
		Ingredient ingredient = ingredientService.findById(id);
		ingredientService.removeAllPizzas(ingredient);
		ingredientService.delete(ingredient);
		ra.addFlashAttribute("updateMessage", "Ingrediente rimosso correttamente");

		return "redirect:/ingredients";
	}
	
	
}
