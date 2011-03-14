package org.exoplatform.jcr.example;

import javax.jcr.AccessDeniedException;
import javax.jcr.ItemExistsException;
import javax.jcr.ItemNotFoundException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.impl.RepositoryServiceImpl;

public class JCRCommandLine {
	private Session session;
	private Node currentNode;

	public Node getCurrentNode() {
		return currentNode;
	}

	public JCRCommandLine(Session session) {
		this.session = session;
		try {
			currentNode = session.getRootNode();
		} catch (RepositoryException e) {
			System.out.println(e.toString());
		}
	}

	public void startCMD() {

	}

	public String processCMD(String cmd) {
		String result = "";
		try {
			if (cmd.startsWith("listNode")) {
				listNode();
			} else if (cmd.startsWith("makeNode ")) {
				createNode(cmd.substring("makeNode ".length()));
			} else if (cmd.startsWith("changeNode ")) {
				 changeNode(cmd.substring("changenode ".length()));
			} else if (cmd.startsWith("deleteNode ")) {
				deleteNode(cmd.substring("deleteNode ".length()));
			}
			session.save();
		} catch (Exception e) {
			result = e.toString();
		}
		
		return result;
	}

	private void createNode(String nodeName) throws ItemExistsException,
			PathNotFoundException, VersionException,
			ConstraintViolationException, LockException, RepositoryException {
		currentNode.addNode(nodeName);
		
	}

	private void deleteNode(String NodeName) throws PathNotFoundException,
			RepositoryException {
		Node nodeToDelete = currentNode.getNode(NodeName);
		nodeToDelete.remove();
	}
	
	private void listNode() throws RepositoryException{
		NodeIterator nodeIterator = currentNode.getNodes();
		while (nodeIterator.hasNext()) {
			System.out.println(nodeIterator.nextNode().getName() + " ");
		}
	}
	
	private void changeNode(String changeTo) throws ItemNotFoundException, AccessDeniedException, RepositoryException{
		if (changeTo.equals("..")) {
			currentNode = currentNode.getParent();
		} else {
			currentNode = currentNode.getNode(changeTo);
		}
	}
}
