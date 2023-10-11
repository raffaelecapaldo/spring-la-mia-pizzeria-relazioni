package org.java.app.business;

import org.java.app.business.db.pojo.Pizza;
import org.java.app.business.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner  {
	
	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza pizza1 = new Pizza("Margherita", "La pizza più semplice", "/img/pizza-margherita.jpg", 4.50f);
		Pizza pizza2 = new Pizza("Capricciosa", "Ogni sfizio è un capriccio", "/img/pizza-capricciosa.jpg", 8.50f);
		Pizza pizza3 = new Pizza("Marinara", "Per stare leggero", "/img/pizza-marinara.jpg", 6f);
		Pizza pizza4 = new Pizza("Napoli", "Con le acciughe fresche", "/img/pizza-napoli.webp", 8f);
		Pizza pizza5 = new Pizza("Wurstel e patatine", "Per tutte le età", "/img/pizza-wurstel.jpg", 5.50f);

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);

		//pizzaService.findAll().forEach(p -> System.out.println(p));
		

		
	}

	

}
