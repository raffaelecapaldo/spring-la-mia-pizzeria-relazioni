package org.java.app.business.db.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class SpecialOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	@Length(min = 3, max = 100, message = "Il nome dell'offerta deve avere una lunghezza dai 3 ai 100 caratteri")
	private String title;
	@FutureOrPresent @NotNull
	private LocalDate startDate;
	@FutureOrPresent @Nullable
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	private static DateTimeFormatter itaFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
	
	public SpecialOffer() { }
	public SpecialOffer(String title, LocalDate startDate, LocalDate endDate, Pizza pizza) {
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setPizza(pizza);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public String getHtmlStartDate() {

		return getStartDate() == null
				? null
				: getStartDate().format(itaFormatter);
	}
	
	public void setHtmlStartDate(String date) {

		setStartDate(LocalDate.parse(date, itaFormatter));
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public String getHtmlEndDate() {

		return getEndDate() == null
				? null
				: getEndDate().format(itaFormatter);
	}
	
	public void setHtmlEndDate(String date) {

		setEndDate(LocalDate.parse(date, itaFormatter));
	}
	
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "[ID]: " + getId() + "\n"
				+ "[Title]: " + getTitle() + "\n"
				+ "[Start date]: " + getHtmlStartDate() + "\n"
				+ "[End date]: " + getHtmlEndDate() + "\n"
				+ "[Pizza ID]: " + getPizza().getId();
	}
	
}
