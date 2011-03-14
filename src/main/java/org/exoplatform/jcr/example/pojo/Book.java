package org.exoplatform.jcr.example.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Book {
	private int id;
	private String title;
	private Calendar publishDay;
	private List<Person> authors;
	private Person owner;
	private double price;
	private boolean borrowed;
	
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
	public Calendar getPublishDay() {
		return publishDay;
	}
	public void setPublishDay(Calendar publishDay) {
		this.publishDay = publishDay;
	}
	public List<Person> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
}
