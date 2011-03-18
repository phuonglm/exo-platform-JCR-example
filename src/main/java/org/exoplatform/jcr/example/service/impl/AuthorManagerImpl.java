package org.exoplatform.jcr.example.service.impl;

import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.service.AuthorManager;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public class AuthorManagerImpl extends Service implements AuthorManager {
	public AuthorManagerImpl(){
		super();
	}
	
	@Override
	public String sayName(){
		return "My Services Name is AuthorManager";
	}
	
	public AuthorManagerImpl(JCRSessionManager sessionManager){
		super(sessionManager);
	}
	public void addAuthor(Person author) {
		// TODO Auto-generated method stub

	}

	public Book getAuthor(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAuthor(int authorID) {
		// TODO Auto-generated method stub
		
	}

}
