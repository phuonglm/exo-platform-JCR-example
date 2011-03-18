package org.exoplatform.jcr.example.service;

import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public interface AuthorManager {
	public void setSessionManager(JCRSessionManager sessionManager);
	public void addAuthor(Person author);
	public Book getAuthor(int ID);
	public void deleteAuthor(int authorID);
	public String sayName();
}