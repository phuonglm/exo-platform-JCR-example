package org.exoplatform.jcr.example.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.ItemExistsException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.service.BookManager;
import org.exoplatform.jcr.example.service.Service;
import org.exoplatform.social.common.jcr.JCRSessionManager;
import org.exoplatform.social.common.jcr.QueryBuilder;

 
public class BookManagerImpl extends Service implements BookManager { 
	private Session session;
	
	public BookManagerImpl() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public BookManagerImpl(JCRSessionManager sessionManager) {
		super(sessionManager);
	}
	
	@Override
	public String sayName(){
		return "My Services Name is BookManager";
	}
	/* (non-Javadoc)
	 * @see org.exoplatform.jcr.example.service.impl.BookManager#addBook(org.exoplatform.jcr.example.pojo.Book)
	 */
	public void addBook(Book book){
		this.session = sessionManager.getOrOpenSession();
		try {
			Node node = session.getRootNode().addNode("book","example:book");

			node.setProperty("title", book.getTitle());
			node.setProperty("price", book.getPrice());
			node.setProperty("publishDay", book.getPublishDay());
			node.setProperty("borrowed" , book.isBorrowed());
			session.save();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchNodeTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sessionManager.closeSession();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.exoplatform.jcr.example.service.impl.BookManager#getBook(int)
	 */
	public Book getBook(String bookID){
		Book result = null;
		this.session = sessionManager.getOrOpenSession();

		Node resultNode;
		try {
			resultNode = session.getNodeByUUID(bookID);
			result = new Book();
			result.setTitle(resultNode.getProperty("title").getValue().getString());
			result.setPrice(resultNode.getProperty("price").getValue().getDouble());
			result.setPublishDay(resultNode.getProperty("publishDay").getValue().getDate());
			result.setBorrowed(resultNode.getProperty("borrowed").getValue().getBoolean());
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.exoplatform.jcr.example.service.impl.BookManager#deleteBook(org.exoplatform.jcr.example.pojo.Book)
	 */
	public void deleteBook(String bookID){
		this.session = sessionManager.getOrOpenSession();
		try {
			Node node = session.getNodeByUUID(bookID);
			node.remove();
			session.save();
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Book> searchBook(Book book) {
		// TODO Auto-generated method stub
		List<Book> result = null;
		this.session = sessionManager.getOrOpenSession();
		QueryBuilder queryBuilder = new QueryBuilder(session);
		queryBuilder = queryBuilder.select("example:book");
		
		if(!book.getTitle().isEmpty()){
			queryBuilder = queryBuilder.equal("title", book.getTitle());
		}
		
		// bla bla search conditional
		
		try {
			List<Node> nodes = queryBuilder.exec();
			if(nodes.size() > 0){
				result = new ArrayList<Book>();
				Book resultBook;
				for (Iterator nodeIterator = nodes.iterator(); nodeIterator.hasNext();) {
					Node node = (Node) nodeIterator.next();
					resultBook = new Book();
					resultBook.setTitle(node.getProperty("title").getString());
					resultBook.setBorrowed(node.getProperty("borrowed").getBoolean());
					resultBook.setPrice(node.getProperty("price").getDouble());
					resultBook.setPublishDay(node.getProperty("publishDay").getDate());
					result.add(resultBook);
				}				
			}
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
		
}
