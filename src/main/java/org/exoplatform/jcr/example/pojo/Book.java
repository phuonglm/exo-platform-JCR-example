package org.exoplatform.jcr.example.pojo;

import java.util.Calendar;
import java.util.List;
import org.chromattic.api.annotations.PrimaryType;

@PrimaryType(name="example:book")
public class Book
{
  public static final String TITLE = "title";
  public static final String PUBLISHDAY = "publishDay";
  public static final String AUTHORS = "authors";
  public static final String OWNER = "owner";
  public static final String PRICE = "price";
  public static final String BORROWED = "borrowed";
  private String id = "";
  private String title = "";
  private Calendar publishDay;
  private List<Person> authors;
  private Person owner;
  private double price;
  private boolean borrowed;

  public String getId()
  {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Calendar getPublishDay() {
    return this.publishDay;
  }
  public void setPublishDay(Calendar publishDay) {
    this.publishDay = publishDay;
  }
  public List<Person> getAuthors() {
    return this.authors;
  }
  public void setAuthors(List<Person> authors) {
    this.authors = authors;
  }
  public Person getOwner() {
    return this.owner;
  }
  public void setOwner(Person owner) {
    this.owner = owner;
  }
  public double getPrice() {
    return this.price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public boolean isBorrowed() {
    return this.borrowed;
  }
  public void setBorrowed(boolean borrowed) {
    this.borrowed = borrowed;
  }
}