package org.exoplatform.jcr.example.pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Person {
	private String name;
	private Calendar birthday;
	List<Book> booksOwner;
	List<Book> booksAuthor;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getBirthday() {
		return birthday;
	}
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	public List<Book> getBooksOwner() {
		return booksOwner;
	}
	public void setBooksOwner(List<Book> booksOwner) {
		this.booksOwner = booksOwner;
	}
	public List<Book> getBooksAuthor() {
		return booksAuthor;
	}
	public void setBooksAuthor(List<Book> booksAuthor) {
		this.booksAuthor = booksAuthor;
	}
}
