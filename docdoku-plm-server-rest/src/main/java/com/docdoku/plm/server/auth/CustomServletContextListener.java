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

package com.docdoku.plm.server.auth;

import com.docdoku.plm.server.config.AuthConfig;

import javax.inject.Inject;
import javax.security.auth.message.config.AuthConfigFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet context listener, register custom auth provider to application
 *
 * @author Morgan Guimard
 */
@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(CustomServletContextListener.class.getName());


    private AuthConfig authConfig;

    @Inject
    public CustomServletContextListener(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        LOGGER.log(Level.INFO, "Registering authentication provider");

        AuthConfigFactory.getFactory()
                .registerConfigProvider(new CustomAuthConfigProvider(authConfig), "HttpServlet",
                        getAppContextID(sce.getServletContext()), "Custom authentication modules registration on HttpServlet layer");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.log(Level.INFO, "Context destroyed");
    }

    public static String getAppContextID(ServletContext context) {
        return context.getVirtualServerName() + " " + context.getContextPath();
    }
}
