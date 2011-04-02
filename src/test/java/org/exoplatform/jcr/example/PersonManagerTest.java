package org.exoplatform.jcr.example;

import java.util.Calendar;

import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import org.exoplatform.services.log.Log;
import org.exoplatform.component.test.ConfiguredBy;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.nodetype.ExtendedNodeTypeManager;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.social.common.jcr.JCRSessionManager;
import org.exoplatform.component.test.AbstractKernelTest;
import org.exoplatform.component.test.ConfigurationUnit;
import org.exoplatform.component.test.ContainerScope;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.jcr.example.pojo.Person;
import org.exoplatform.jcr.example.service.PersonManager;


@ConfiguredBy({
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.test.jcr-configuration.xml"),
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.test.organization-configuration.xml"),
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/portal/jcr-example-test-configuration.xml")
		})
public class PersonManagerTest extends AbstractKernelTest {
	private final Log LOG = ExoLogger.getLogger(PersonManagerTest.class);
	PortalContainer portalContainer;
	RepositoryService repositoryService;
	JCRSessionManager sessionManager;
	ExtendedNodeTypeManager nodeTypeManager;
	private final String WORKSPACE = "portal-test";

	@Override
	protected void beforeRunBare() throws Exception {
		super.beforeRunBare();
		portalContainer = PortalContainer.getInstance();
		repositoryService = (RepositoryService) portalContainer
				.getComponentInstanceOfType(RepositoryService.class);
		sessionManager = new JCRSessionManager(WORKSPACE, repositoryService);
	}

	@Override
	protected void afterRunBare() {
		super.afterRunBare();
	}

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
		sessionManager.closeSession();
		Session session = sessionManager.getOrOpenSession();
		try {
			QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			Query query = queryManager.createQuery("select * from example:person",
					Query.SQL);
			QueryResult result = query.execute();
			NodeIterator nodeIterator = result.getNodes();

			while (nodeIterator.hasNext()) {
				nodeIterator.nextNode().remove();
			}

			session.save();
		} finally {
			sessionManager.closeSession();
		}
	}

	public void testAuthorService() {
		PersonManager authorManager = (PersonManager)portalContainer.getComponentInstanceOfType(PersonManager.class);
		Person author = new Person();
		author.setName("Ly Minh Phuong");
		author.setBirthday( Calendar.getInstance());
		authorManager.addPerson(author);
		assertEquals(authorManager.searchPerson(author).size(), 1);
		
//		BookManager bookManager = (BookManager) portalContainer.getComponentInstanceOfType(BookManager.class);
//		bookManager.setSessionManager(sessionManager);
//		Book book = new Book();
//		book.setTitle("hello girl");
//		book.setPrice(0);
//		book.setBorrowed(false);
//		book.setPublishDay(Calendar.getInstance());
//		bookManager.addBook(book);
//		assertEquals(bookManager.searchBook(book).size(), 1);
	}
}
