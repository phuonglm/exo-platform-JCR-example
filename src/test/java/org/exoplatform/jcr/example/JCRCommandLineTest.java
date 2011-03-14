package org.exoplatform.jcr.example;

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
import org.exoplatform.jcr.example.JCRCommandLine;
import org.exoplatform.jcr.example.pojo.Book;
import org.exoplatform.jcr.example.service.BookManager;
import org.exoplatform.jcr.example.service.impl.BookManagerImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import org.exoplatform.services.jcr.core.nodetype.NodeTypeValue;
import org.exoplatform.services.jcr.core.nodetype.PropertyDefinitionValue;
import org.exoplatform.services.jcr.impl.core.ExtendedNamespaceRegistry;

@ConfiguredBy({
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.test.jcr-configuration.xml"),
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.test.organization-configuration.xml"),
		@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/jcr-test-configuration.xml")
		})
public class JCRCommandLineTest extends AbstractKernelTest {
	private final Log LOG = ExoLogger.getLogger(JCRCommandLineTest.class);
	PortalContainer portalContainer;
	RepositoryService repositoryService;
	JCRSessionManager sessionManager;
	ExtendedNodeTypeManager nodeTypeManager;
	private Session session;
	private final String WORKSPACE = "portal-test";

	@Override
	protected void beforeRunBare() throws Exception {
		super.beforeRunBare();
		portalContainer = PortalContainer.getInstance();
		repositoryService = (RepositoryService) portalContainer
				.getComponentInstanceOfType(RepositoryService.class);
		sessionManager = new JCRSessionManager(WORKSPACE, repositoryService);

		session = sessionManager.getOrOpenSession();
		try {
			nodeTypeManager = (ExtendedNodeTypeManager) session.getWorkspace()
					.getNodeTypeManager();
			addNodeTypes();
		} finally {
			sessionManager.closeSession();
		}
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
//		sessionManager.closeSession();
//		Session session = sessionManager.getOrOpenSession();
//		try {
//			QueryManager queryManager = session.getWorkspace()
//					.getQueryManager();
//
//			Query query = queryManager.createQuery("select * from exo:book",
//					Query.SQL);
//			QueryResult result = query.execute();
//			NodeIterator nodeIterator = result.getNodes();
//
//			while (nodeIterator.hasNext()) {
//				nodeIterator.nextNode().remove();
//			}
//
//			session.save();
//		} finally {
//			sessionManager.closeSession();
//		}
	}

	public void testBookService() {
		BookManager bookManager = (BookManager) portalContainer.getComponentInstanceOfType(BookManager.class);
		bookManager.setSessionManager(sessionManager);
		Book book = new Book();
		book.setTitle("hello girl");
		book.setPrice(0);
		book.setBorrowed(false);
		book.setPublishDay(Calendar.getInstance());
		bookManager.addBook(book);
		assertEquals(bookManager.searchBook(book).size(), 1);
	}

	public void testProcessCMD() {
		JCRCommandLine commandLine = new JCRCommandLine(session);

		try {
			commandLine.processCMD("makeNode test:book");
			commandLine.processCMD("changeNode test:book");
			assertEquals("test:book", commandLine.getCurrentNode().getName());
			commandLine.processCMD("changeNode ..");
			commandLine.processCMD("makeNode test:person");
			commandLine.processCMD("changeNode test:person");
			assertEquals("test:person", commandLine.getCurrentNode().getName());
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sessionManager.closeSession();
		}			
	}
	private void addNodeTypes() throws RepositoryException {
//		ExtendedNamespaceRegistry namespaceRegistry = (ExtendedNamespaceRegistry) session.getWorkspace().getNamespaceRegistry();
//		namespaceRegistry.registerNamespace("test", "http://exoplatform.org/testNameSpace");
//		NodeTypeValue zTypeValue = new NodeTypeValue();
//		List<String> superTypes = new ArrayList<String>();
//		superTypes.add("nt:base");
//
//		zTypeValue.setName("test:book");
//		zTypeValue.setPrimaryItemName("test:primary");
//		zTypeValue.setDeclaredSupertypeNames(superTypes);
//
//		List<PropertyDefinitionValue> properties = new ArrayList<PropertyDefinitionValue>();
//		properties.add(new PropertyDefinitionValue("title", false, true, 1,
//				false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//		properties.add(new PropertyDefinitionValue("publisher", false, true, 1,
//				false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//		properties.add(new PropertyDefinitionValue("date", false, false, 1,
//				false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//		properties.add(new PropertyDefinitionValue("owner", false, false, 1,
//				false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//		properties.add(new PropertyDefinitionValue("price", false, false, 1,
//				false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//		properties.add(new PropertyDefinitionValue("isborrowed", false, false,
//				1, false, new ArrayList<String>(), false, 0,
//				new ArrayList<String>()));
//
//		zTypeValue.setDeclaredPropertyDefinitionValues(properties);
//
//		nodeTypeManager.registerNodeType(zTypeValue,
//				ExtendedNodeTypeManager.IGNORE_IF_EXISTS);


	}

	private void removeNodeTypes() throws RepositoryException {
		nodeTypeManager.unregisterNodeType("exo:book");
	}
	
	

}
