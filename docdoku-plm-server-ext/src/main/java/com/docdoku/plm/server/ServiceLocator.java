/*
 * DocDoku, Professional Open Source
 * Copyright 2006 - 2020 DocDoku SARL
 *
 * This file is part of DocDokuPLM.
 *
 * DocDokuPLM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDokuPLM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with DocDokuPLM.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.docdoku.plm.server;

import com.docdoku.plm.server.core.services.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ServiceLocator class helps to inject EJB services outside injection context
 */
@ApplicationScoped
public class ServiceLocator {

    private static final String STORAGE_MANAGER = "java:app/docdoku-server-ejb/BinaryStorageManagerBean!com.docdoku.plm.server.core.services.IBinaryStorageManagerLocal";
    private static final String PRODUCT_MANAGER = "java:app/docdoku-server-ejb/ProductManagerBean!com.docdoku.plm.server.core.services.IProductManagerLocal";
    private static final String PRODUCT_INSTANCE_MANAGER = "java:app/docdoku-server-ejb/ProductInstanceManagerBean!com.docdoku.plm.server.core.services.IProductInstanceManagerLocal";
    private static final String USER_MANAGER = "java:app/docdoku-server-ejb/UserManagerBean!com.docdoku.plm.server.core.services.IUserManagerLocal";
    private static final String SHARE_MANAGER = "java:app/docdoku-server-ejb/ShareManagerBean!com.docdoku.plm.server.core.services.IShareManagerLocal";
    private static final String PART_WORKFLOW_MANAGER = "java:app/docdoku-server-ejb/PartWorkflowManagerBean!com.docdoku.plm.server.core.services.IPartWorkflowManagerLocal";
    private static final String DOCUMENT_WORKFLOW_MANAGER = "java:app/docdoku-server-ejb/DocumentWorkflowManagerBean!com.docdoku.plm.server.core.services.IDocumentWorkflowManagerLocal";
    private static final String CASCADE_ACTION_MANAGER = "java:app/docdoku-server-ejb/CascadeActionManagerBean!com.docdoku.plm.server.core.services.ICascadeActionManagerLocal";
    private static final String LOV_MANAGER = "java:app/docdoku-server-ejb/LOVManagerBean!com.docdoku.plm.server.core.services.ILOVManagerLocal";

    private static final Logger LOGGER = Logger.getLogger(ServiceLocator.class.getName());

    private Context context;

    @PostConstruct
    private void init() {
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @InternalService
    @Produces
    public IProductManagerLocal findProductManager() throws NamingException {
        return (IProductManagerLocal) context.lookup(PRODUCT_MANAGER);
    }

    @InternalService
    @Produces
    public IBinaryStorageManagerLocal findStorageManager() throws NamingException {
        return (IBinaryStorageManagerLocal) context.lookup(STORAGE_MANAGER);
    }

    @InternalService
    @Produces
    public IProductInstanceManagerLocal findProductInstanceManager() throws NamingException {
        return (IProductInstanceManagerLocal) context.lookup(PRODUCT_INSTANCE_MANAGER);
    }

    @InternalService
    @Produces
    public IUserManagerLocal findUserManager() throws NamingException {
        return (IUserManagerLocal) context.lookup(USER_MANAGER);
    }

    @InternalService
    @Produces
    public IShareManagerLocal findShareManager() throws NamingException {
        return (IShareManagerLocal) context.lookup(SHARE_MANAGER);
    }

    @InternalService
    @Produces
    public IPartWorkflowManagerLocal findPartWorkflowManager() throws NamingException {
        return (IPartWorkflowManagerLocal) context.lookup(PART_WORKFLOW_MANAGER);
    }

    @InternalService
    @Produces
    public IDocumentWorkflowManagerLocal findDocumentWorkflowManager() throws NamingException {
        return (IDocumentWorkflowManagerLocal) context.lookup(DOCUMENT_WORKFLOW_MANAGER);
    }

    @InternalService
    @Produces
    public ICascadeActionManagerLocal findCascadeActionManager() throws NamingException {
        return (ICascadeActionManagerLocal) context.lookup(CASCADE_ACTION_MANAGER);
    }

    @InternalService
    @Produces
    public ILOVManagerLocal findLOVManager() throws NamingException {
        return (ILOVManagerLocal) context.lookup(LOV_MANAGER);
    }

}
