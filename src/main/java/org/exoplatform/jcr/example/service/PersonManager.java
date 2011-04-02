package org.exoplatform.jcr.example.service;

import java.util.List;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.pojo.Person;

public abstract interface PersonManager
{
  public abstract void addPerson(Person paramPerson);

  public abstract Book getPerson(String paramString);

  public abstract void deletePerson(String paramString);

  public abstract List<Person> searchPerson(Person paramPerson);

  public abstract String sayName();
}