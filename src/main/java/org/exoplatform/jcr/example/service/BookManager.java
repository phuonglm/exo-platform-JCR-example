package org.exoplatform.jcr.example.service;

import java.util.List;
import org.exoplatform.jcr.example.pojo.Book;

public interface BookManager {
	public void save(Book paramBook);

	public void delete(String paramString);

	public void delete(Book paramBook);

	public List<Book> search(Book paramBook);

	public String sayName();
}