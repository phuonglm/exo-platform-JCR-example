package org.exoplatform.jcr.example.service;

import javax.jcr.Session;

import org.exoplatform.social.common.jcr.JCRSessionManager;

public abstract class Service {
	protected JCRSessionManager sessionManager;
	public Service(){		
	}
	
	public Service(JCRSessionManager sessionManager) {
		// TODO Auto-generated constructor stub
		this.sessionManager = sessionManager;
	}
	public void setSessionManager(JCRSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
	
	public String sayName(){
		return "My Services Name";
	}
}
