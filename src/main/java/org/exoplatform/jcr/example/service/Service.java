package org.exoplatform.jcr.example.service;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.social.common.jcr.JCRSessionManager;

public abstract class Service
{
  public String workspace = "portal-system";
  protected JCRSessionManager sessionManager;

  public Service()
  {
  }

  public Service(String workspace)
  {
    this.workspace = workspace;
  }

  public String sayName() {
    return "My Services Name";
  }

  protected JCRSessionManager getSessionManager() {
    PortalContainer portalContainer = PortalContainer.getInstance();
    RepositoryService repositoryService = (RepositoryService)portalContainer.getComponentInstanceOfType(RepositoryService.class);
    return new JCRSessionManager(this.workspace, repositoryService);
  }

  protected String getParam(InitParams params, String name, String defaultValue) {
    String result = null;
    try {
      result = params.getValueParam(name).getValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (result == null) {
      result = defaultValue;
    }
    return result;
  }
}