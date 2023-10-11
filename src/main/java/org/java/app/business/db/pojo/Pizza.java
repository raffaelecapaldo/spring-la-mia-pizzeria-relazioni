package org.java.app.business.db.pojo;


import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	@Length(min = 3, max = 100, message = "Il nome può avere soltanto una lunghezza dai 3 ai 100 caratteri")
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
	
	public Pizza() { }
	public Pizza(String name, String description, String imageUrl, float price) throws Exception {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
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
