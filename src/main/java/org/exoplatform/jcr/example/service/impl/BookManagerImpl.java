package org.exoplatform.jcr.example.service.impl;

import java.util.List;
import javax.jcr.Session;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.service.BookManager;
import org.exoplatform.jcr.example.service.BookStorage;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public class BookManagerImpl extends Service
  implements BookManager
{
  private Session session;

  public BookManagerImpl()
  {
  }

  public BookManagerImpl(InitParams initParams)
  {
    this.workspace = getParam(initParams, "workspace", "portal-system");
  }

  public String sayName() {
    return "My Services Name is BookManager";
  }

  public void save(Book book)
  {
    try
    {
      BookStorage bookStorage = (BookStorage)PortalContainer.getInstance().getComponentInstanceOfType(BookStorage.class);
      if(book.getOwner() != null){
    	  Person owner = book.getOwner();
    	  if(owner.getId() == null || owner.getId().isEmpty()){
    		  
    	  }
      }
      bookStorage.create(book);

    } catch (JCRCRUDException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Book> search(Book book)
  {
    this.session = getSessionManager().getOrOpenSession();
    BookStorage bookStorage = (BookStorage)PortalContainer.getInstance().getComponentInstanceOfType(BookStorage.class);
    getSessionManager().closeSession();
    return bookStorage.select(book);
  }

  public void delete(String bookID)
  {
    try
    {
      BookStorage bookStorage = (BookStorage)PortalContainer.getInstance().getComponentInstanceOfType(BookStorage.class);
      bookStorage.delete(bookID);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(Book book)
  {
    try {
      BookStorage bookStorage = (BookStorage)PortalContainer.getInstance().getComponentInstanceOfType(BookStorage.class);
      bookStorage.delete(book);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}