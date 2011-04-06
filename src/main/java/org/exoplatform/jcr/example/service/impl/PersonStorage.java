package org.exoplatform.jcr.example.service.impl;

import java.util.List;

import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Person;

public interface PersonStorage {

	public abstract Person create(Person personToCreate)
			throws JCRCRUDException;

	public abstract void update(Person personToUpdate) throws JCRCRUDException;

	public abstract void delete(Person personToDelete) throws JCRCRUDException;

	public abstract void delete(String personToDeleteID)
			throws JCRCRUDException;

	public abstract List<Person> select(Person personToSelect);

}