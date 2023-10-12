package org.java.app.business.controller;


import org.java.app.business.db.pojo.Ingredient;
import org.java.app.business.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String storeIngredient(Ingredient ingredient, BindingResult bindingResult, Model model, 
			RedirectAttributes ra) {
		if (bindingResult.hasErrors()) {
			 return "ingredient/ingredient-create";
		}
		else {
			ingredientService.save(ingredient);
			ra.addFlashAttribute("addedMessage", "Ingrediente aggiunto correttamente");
			return "redirect:/ingredients";
		}
		
	}
	
}
