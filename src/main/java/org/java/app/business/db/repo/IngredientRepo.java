package org.java.app.business.db.repo;

import org.java.app.business.db.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {

}
