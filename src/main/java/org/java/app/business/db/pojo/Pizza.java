package org.java.app.business.db.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	@Length(min = 3, max = 100, message = "Il nome deve avere una lunghezza dai 3 ai 100 caratteri")
	private String name;
	@Nullable
	@Length(max = 255, message = "La descrizione non può essere più lunga di 255 caratteri")
	private String description;
	@Nullable
	@Length(max = 255, message = "L'indirizzo web non può essere più lungo di 255 caratteri")
	private String imageUrl;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.01", message = "Il prezzo della pizza non può essere zero o inferiore")
	private float price;
	
	@OneToMany(mappedBy = "pizza")
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	private List<Ingredient> ingredients;

	
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}
	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	
	
	public Pizza() { }
	public Pizza(String name, String description, String imageUrl, float price,
			Ingredient...ingredients) throws Exception {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setIngredients(Arrays.asList(ingredients));
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public boolean hasIngredient(Ingredient ingredient) {
			
			if (getIngredients() == null) return false;
			
			for (Ingredient ingr : getIngredients()) 
				if (ingr.getId() == ingredient.getId())
					return true;
			
			return false;
		}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price*100;
	}
	
	@Override
	public String toString() {
		return "[ID]: " + getId() + "\n"
				+ "[Name]: " + getName() + "\n"
				+ "[Description]: " + getDescription() + "\n"
				+ "[Image url]: " + getImageUrl() + "\n"
				+ "[Price]: " + getPrice()/100;
	}
	
	
	
}
