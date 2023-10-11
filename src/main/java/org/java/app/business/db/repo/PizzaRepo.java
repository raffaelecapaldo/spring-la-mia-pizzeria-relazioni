package org.java.app.business.db.repo;

import java.util.List;

import org.java.app.business.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	public List<Pizza> findByNameContaining(String name);

	
}
