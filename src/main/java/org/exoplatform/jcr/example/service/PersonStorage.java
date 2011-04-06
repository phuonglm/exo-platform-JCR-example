package org.exoplatform.jcr.example.service;

import java.util.List;

import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Person;

public interface PersonStorage {

	public Person create(Person personToCreate) throws JCRCRUDException;

	public void update(Person personToUpdate) throws JCRCRUDException;

	public void delete(Person personToDelete) throws JCRCRUDException;

	public void delete(String personToDeleteID) throws JCRCRUDException;

	public List<Person> select(Person personToSelect);

}