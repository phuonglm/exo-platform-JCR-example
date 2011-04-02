package org.exoplatform.jcr.example.service;

import java.util.List;
import org.exoplatform.jcr.example.exception.JCRCRUDException;
import org.exoplatform.jcr.example.pojo.Book;

public abstract interface BookStorage
{
  public abstract List<Book> select(Book paramBook);

  public abstract Book create(Book paramBook)
    throws JCRCRUDException;

  public abstract void update(Book paramBook)
    throws JCRCRUDException;

  public abstract void delete(Book paramBook)
    throws JCRCRUDException;

  public abstract void delete(String paramString)
    throws JCRCRUDException;
}

/* Location:           /home/phuong_lyminh/EclipseWorkspace/training/exojcr/target/exojcr-0.0.1-SNAPSHOT.jar
 * Qualified Name:     org.exoplatform.jcr.example.service.BookStorage
 * JD-Core Version:    0.6.0
 */