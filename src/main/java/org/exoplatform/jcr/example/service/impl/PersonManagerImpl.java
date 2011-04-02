package org.exoplatform.jcr.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.service.PersonManager;
import org.exoplatform.jcr.example.service.Service;

public class PersonManagerImpl extends Service
  implements PersonManager
{
  public PersonManagerImpl()
  {
  }

  public PersonManagerImpl(InitParams initParams)
  {
    this.workspace = getParam(initParams, "workspace", "portal-system");
  }

  public String sayName()
  {
    return "My Services Name is PersonManager";
  }

  public PersonManagerImpl(String workspace) {
    super(workspace);
  }

  public void deleteAuthor(int authorID)
  {
  }

  public void addPerson(Person person)
  {
  }

  public Book getPerson(String personID)
  {
    return null;
  }

  public void deletePerson(String personID)
  {
  }

  public List<Person> searchPerson(Person person)
  {
    ArrayList results = new ArrayList();
    results.add(person);
    return results;
  }
}