package org.exoplatform.jcr.example.service;

import java.util.List;

import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public interface BookManager {
	public void setSessionManager(JCRSessionManager sessionManager);
	public void addBook(Book book);
	public Book getBook(String bookID);
	public void deleteBook(String bookID); 
	public List<Book> searchBook(Book book);

}