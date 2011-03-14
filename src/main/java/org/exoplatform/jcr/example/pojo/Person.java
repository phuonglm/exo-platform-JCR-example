package org.exoplatform.jcr.example.pojo;

import java.util.Date;
import java.util.List;

public class Person {
	private String name;
	private Date birthday;
	List<Book> booksOwner;
	List<Book> booksAuthor;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
