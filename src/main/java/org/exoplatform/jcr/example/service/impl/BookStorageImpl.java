package org.exoplatform.jcr.example.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.service.BookStorage;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.QueryBuilder;

public class BookStorageImpl extends Service
  implements BookStorage
{
  public static final int LIMIT = 50;

  public BookStorageImpl(InitParams initParams)
  {
    this.workspace = getParam(initParams, "workspace", "portal-system");
  }

  public Book create(Book bookToCreate) throws JCRCRUDException {
    if (bookToCreate != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        Node node = session.getRootNode().addNode("books", "example:book");
        node.setProperty(Book.TITLE, bookToCreate.getTitle());
        node.setProperty(Book.PRICE, bookToCreate.getPrice());
        node.setProperty(Book.PUBLISHDAY, bookToCreate.getPublishDay());
        node.setProperty(Book.BORROWED, bookToCreate.isBorrowed());
        bookToCreate.setId(node.getUUID());
      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
    return bookToCreate;
  }

  public void update(Book bookToUpdate) throws JCRCRUDException {
    if (bookToUpdate != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        Node node = session.getNodeByUUID(bookToUpdate.getId());
        node.setProperty(Book.TITLE, bookToUpdate.getTitle());
        node.setProperty(Book.PRICE, bookToUpdate.getPrice());
        node.setProperty(Book.PUBLISHDAY, bookToUpdate.getPublishDay());
        node.setProperty(Book.BORROWED, bookToUpdate.isBorrowed());
      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
  }

  public void delete(Book bookToDelete) throws JCRCRUDException
  {
    if (bookToDelete != null) {
      if (!bookToDelete.getId().isEmpty()) {
        delete(bookToDelete.getId());
      } else {
        List<Book> books = select(bookToDelete);
        if (books.size() > 0) {
          for (Book book : books)
            delete(book.getId());
        }
      }
    }
    else
      throw new JCRCRUDException("invalid param.");
  }

  public void delete(String bookToDeleteID) throws JCRCRUDException
  {
    if (bookToDeleteID != null) {
      Session session = getSessionManager().getOrOpenSession();
      try {
        session.getNodeByUUID(bookToDeleteID).remove();
      } catch (Exception e) {
        throw new JCRCRUDException(e.getMessage());
      } finally {
        getSessionManager().closeSession(true);
      }
    }
  }

  public List<Book> select(Book bookToSelect)
  {
    List result = new ArrayList();
    if (bookToSelect != null) {
      Session session = getSessionManager().getOrOpenSession();

      if (!bookToSelect.getId().isEmpty()) {
        try {
          Node node = session.getNodeByUUID(bookToSelect.getId());
          Book resultBook = new Book();
          resultBook.setId(node.getUUID());
          resultBook.setTitle(node.getProperty(Book.TITLE).getString());
          resultBook.setBorrowed(node.getProperty(Book.BORROWED).getBoolean());
          resultBook.setPrice(node.getProperty(Book.PRICE).getDouble());
          resultBook.setPublishDay(node.getProperty(Book.PUBLISHDAY).getDate());
          result.add(resultBook);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        QueryBuilder queryBuilder = new QueryBuilder(session);
        queryBuilder = queryBuilder.select("example:book");

        if (!bookToSelect.getTitle().isEmpty()) {
          queryBuilder = queryBuilder.like(Book.TITLE, "%" + bookToSelect.getTitle() + "%");
        }
        try
        {
          List<Node> nodes = queryBuilder.exec();
          if (nodes.size() > 0)
          {
            for (Iterator<Node> nodeIterator = nodes.iterator(); nodeIterator.hasNext(); ) {
              Node node = (Node)nodeIterator.next();
              Book resultBook = new Book();
              resultBook.setId(node.getUUID());
              resultBook.setTitle(node.getProperty(Book.TITLE).getString());
              resultBook.setBorrowed(node.getProperty(Book.BORROWED).getBoolean());
              resultBook.setPrice(node.getProperty(Book.PRICE).getDouble());
              resultBook.setPublishDay(node.getProperty(Book.PUBLISHDAY).getDate());
              result.add(resultBook);
            }
          }
        }
        catch (RepositoryException e)
        {
          Iterator nodeIterator;
          e.printStackTrace();
        }
      }
    }
    return result;
  }
}