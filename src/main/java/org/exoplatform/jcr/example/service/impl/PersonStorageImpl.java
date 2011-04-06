package org.exoplatform.jcr.example.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.service.PersonStorage;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.QueryBuilder;

public class PersonStorageImpl extends Service
  implements PersonStorage
{
  public static final int LIMIT = 50;

  public PersonStorageImpl(InitParams initParams)
  {
    this.workspace = getParam(initParams, "workspace", "portal-system");
  }

  /* (non-Javadoc)
 * @see org.exoplatform.jcr.example.service.impl.PersonStorage#create(org.exoplatform.jcr.example.pojo.Person)
 */
@Override
public Person create(Person personToCreate) throws JCRCRUDException {
    if (personToCreate != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        Node node = session.getRootNode().addNode("persons", "example:person");
        node.setProperty(Person.NAME, personToCreate.getName());
        node.setProperty(Person.BIRTHDAY, personToCreate.getBirthday());
        personToCreate.setId(node.getUUID());
      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
    return personToCreate;
  }

  /* (non-Javadoc)
 * @see org.exoplatform.jcr.example.service.impl.PersonStorage#update(org.exoplatform.jcr.example.pojo.Person)
 */
@Override
public void update(Person personToUpdate) throws JCRCRUDException {
    if (personToUpdate != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        Node node = session.getNodeByUUID(personToUpdate.getId());
        node.setProperty(Person.NAME, personToUpdate.getName());
        node.setProperty(Person.BIRTHDAY, personToUpdate.getBirthday());

      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
  }

  /* (non-Javadoc)
 * @see org.exoplatform.jcr.example.service.impl.PersonStorage#delete(org.exoplatform.jcr.example.pojo.Person)
 */
@Override
public void delete(Person personToDelete) throws JCRCRUDException
  {
    if (personToDelete != null) {
      if (!personToDelete.getId().isEmpty()) {
        delete(personToDelete.getId());
      } else {
        List<Person> persons = select(personToDelete);
        if (persons.size() > 0) {
          for (Person person : persons)
            delete(person.getId());
        }
      }
    }
    else
      throw new JCRCRUDException("invalid param.");
  }

  /* (non-Javadoc)
 * @see org.exoplatform.jcr.example.service.impl.PersonStorage#delete(java.lang.String)
 */
@Override
public void delete(String personToDeleteID) throws JCRCRUDException
  {
    if (personToDeleteID != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        session.getNodeByUUID(personToDeleteID).remove();
      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
  }

  /* (non-Javadoc)
 * @see org.exoplatform.jcr.example.service.impl.PersonStorage#select(org.exoplatform.jcr.example.pojo.Person)
 */
@Override
public List<Person> select(Person personToSelect)
  {
    List<Person> result = new ArrayList();
    if (personToSelect != null) {
      Session session = getSessionManager().getOrOpenSession();

      if (!personToSelect.getId().isEmpty()) {
        try {
          Node node = session.getNodeByUUID(personToSelect.getId());
          Person resultPerson = new Person();
          resultPerson.setId(node.getUUID());
          resultPerson.setName(node.getProperty(Person.NAME).getString());
          resultPerson.setBirthday(node.getProperty(Person.BIRTHDAY).getDate());
          result.add(resultPerson);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        QueryBuilder queryBuilder = new QueryBuilder(session);
        queryBuilder = queryBuilder.select("example:person");

        if (!personToSelect.getName().isEmpty()) {
          queryBuilder = queryBuilder.like("title", "%" + personToSelect.getName() + "%");
        }
        try
        {
          List<Node> nodes = queryBuilder.exec();
          if (nodes.size() > 0)
          {
            for (Iterator<Node> nodeIterator = nodes.iterator(); nodeIterator.hasNext(); ) {
              Node node = (Node)nodeIterator.next();
              Person resultPerson = new Person();
              resultPerson.setId(node.getUUID());
              resultPerson.setName(node.getProperty(Person.NAME).getString());
              resultPerson.setBirthday(node.getProperty(Person.BIRTHDAY).getDate());
              result.add(resultPerson);
            }
          }
        }
        catch (RepositoryException e)
        {
          e.printStackTrace();
        }
      }
    }
    return result;
  }
}