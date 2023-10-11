package org.java.app.business.db.serv;

import java.util.List;

import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}
	
	public void delete(Pizza pizza) {
		pizzaRepo.delete(pizza);
	}
	
	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}
	
	public Pizza findById(int id) {
		return pizzaRepo.findById(id).get();
	}
	
	public List<Pizza> findByName(String name) {
			
			return pizzaRepo.findByNameContaining(name);
		}

}
