package org.java.app.business.db.serv;

import java.util.List;

import org.java.app.business.db.pojo.Ingredient;
import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public void delete(Ingredient ingredient) {
		ingredientRepo.delete(ingredient);
	}
	
	public void removeAllPizzas(Ingredient ingredient) {
		List<Pizza> pizzas = ingredient.getPizzas();
		for (Pizza pizza : pizzas) {
			List<Ingredient> allIngredients = pizza.getIngredients();
			allIngredients.remove(ingredient);
			pizza.setIngredients(allIngredients);
			}
		}
	
	
	public Ingredient findById(int id) {
		return ingredientRepo.findById(id).get();
		}
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}
}
