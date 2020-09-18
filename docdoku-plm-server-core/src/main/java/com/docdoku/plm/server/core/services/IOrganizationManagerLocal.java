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

package com.docdoku.plm.server.core.services;

import com.docdoku.plm.server.core.common.Organization;
import com.docdoku.plm.server.core.exceptions.*;

/**
 *
 * @author Elisabel Généreux
 */
public interface IOrganizationManagerLocal {

    Organization getOrganizationOfAccount(String pLogin) throws AccountNotFoundException, OrganizationNotFoundException;
    Organization getMyOrganization() throws AccountNotFoundException, OrganizationNotFoundException;
    Organization createOrganization(String pName, String pDescription) throws OrganizationAlreadyExistsException, CreationException, NotAllowedException, AccountNotFoundException;
    void deleteOrganization(String pName) throws OrganizationNotFoundException, AccountNotFoundException, AccessRightException;
    void updateOrganization(Organization pOrganization) throws AccountNotFoundException, OrganizationNotFoundException, AccessRightException;
    void addAccountInOrganization(String pOrganizationName, String pLogin) throws OrganizationNotFoundException, AccountNotFoundException, NotAllowedException, AccessRightException;
    void removeAccountsFromOrganization(String pOrganizationName, String[] pLogins) throws OrganizationNotFoundException, AccessRightException, AccountNotFoundException;

}
