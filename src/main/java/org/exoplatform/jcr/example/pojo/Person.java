package org.exoplatform.jcr.example.pojo;

import java.util.Calendar;
import java.util.List;
import org.chromattic.api.annotations.PrimaryType;

@PrimaryType(name = "example:person")
public class Person {
	private String id;
	private String name;
	private Calendar birthday;
	List<Book> booksOwner;
	List<Book> booksAuthor;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public List<Book> getBooksOwner() {
		return this.booksOwner;
	}

	public void setBooksOwner(List<Book> booksOwner) {
		this.booksOwner = booksOwner;
	}

	public List<Book> getBooksAuthor() {
		return this.booksAuthor;
	}

	public void setBooksAuthor(List<Book> booksAuthor) {
		this.booksAuthor = booksAuthor;
	}

}