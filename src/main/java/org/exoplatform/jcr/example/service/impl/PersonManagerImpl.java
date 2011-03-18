package org.exoplatform.jcr.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.service.PersonManager;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public class PersonManagerImpl extends Service implements PersonManager {
	public PersonManagerImpl(){
		super();
	}
	
	@Override
	public String sayName(){
		return "My Services Name is PersonManager";
	}
	
	public PersonManagerImpl(JCRSessionManager sessionManager){
		super(sessionManager);
	}


	public void deleteAuthor(int authorID) {
		// TODO Auto-generated method stub
		
	}

	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	public Book getPerson(String personID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePerson(String personID) {
		// TODO Auto-generated method stub
		
	}

	public List<Person> searchPerson(Person person) {
		// TODO Auto-generated method stub
		ArrayList<Person> results = new ArrayList<Person>();
		results.add(person);
		return results;
	}

}
