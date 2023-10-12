package org.java.app.business;

import java.time.LocalDate;

import org.java.app.business.db.pojo.Ingredient;
import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.pojo.SpecialOffer;
import org.java.app.business.db.serv.IngredientService;
import org.java.app.business.db.serv.PizzaService;
import org.java.app.business.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner  {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient pomodoro = new Ingredient("Pomodoro");
		Ingredient mozzarella = new Ingredient("Mozzarella");
		Ingredient acciughe = new Ingredient("Acciughe");
		Ingredient wurstel = new Ingredient("Wurstel");
		
		ingredientService.save(pomodoro);
		ingredientService.save(mozzarella);
		ingredientService.save(acciughe);
		ingredientService.save(wurstel);

		
		Pizza pizza1 = new Pizza("Margherita", "La pizza più semplice", "/img/pizza-margherita.jpg", 4.50f, pomodoro, mozzarella);
		Pizza pizza2 = new Pizza("Capricciosa", "Ogni sfizio è un capriccio", "/img/pizza-capricciosa.jpg", 8.50f, pomodoro, mozzarella);
		Pizza pizza3 = new Pizza("Marinara", "Per stare leggero", "/img/pizza-marinara.jpg", 6f, pomodoro);
		Pizza pizza4 = new Pizza("Napoli", "Con le acciughe fresche", "/img/pizza-napoli.webp", 8f, pomodoro, acciughe);
		Pizza pizza5 = new Pizza("Wurstel e patatine", "Per tutte le età", "/img/pizza-wurstel.jpg", 5.50f, wurstel);

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
		SpecialOffer sp1 = new SpecialOffer("Due pizza al prezzo di una", LocalDate.now(), LocalDate.now().plusDays(2), pizza1);
		SpecialOffer sp2 = new SpecialOffer("Bibite gratis", LocalDate.now(), LocalDate.now().plusDays(2), pizza1);
		SpecialOffer sp3 = new SpecialOffer("Buono sconto 20% prossimo ordine", LocalDate.now(), LocalDate.now().plusDays(2), pizza2);

		

		specialOfferService.save(sp1);
		specialOfferService.save(sp2);
		specialOfferService.save(sp3);

		

		//pizzaService.findAll().forEach(p -> System.out.println(p));
		

		
	}

	

}
