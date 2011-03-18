package org.exoplatform.jcr.example.service;

import java.util.List;

import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public interface PersonManager {
	public void setSessionManager(JCRSessionManager sessionManager);
	public void addPerson(Person person);
	public Book getPerson(String personID);
	public void deletePerson(String personID);
	public List<Person> searchPerson(Person person);
	public String sayName();
}