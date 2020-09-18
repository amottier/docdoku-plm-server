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

import com.docdoku.plm.server.core.common.Account;
import com.docdoku.plm.server.core.common.Organization;
import com.docdoku.plm.server.core.exceptions.*;
import com.docdoku.plm.server.core.security.UserGroupMapping;

import java.util.List;

/**
 * @author Elisabel Généreux
 */
public interface IAccountManagerLocal {

    Account getAccount(String pLogin) throws AccountNotFoundException;

    String getRole(String pLogin);

    Account createAccount(String pLogin, String pName, String pEmail, String pLanguage, String pPassword, String pTimeZone) throws AccountAlreadyExistsException, CreationException;

    Account updateAccount(String pName, String pEmail, String pLanguage, String pPassword, String pTimeZone) throws AccountNotFoundException, NotAllowedException;

    Account updateAccount(String pLogin, String pName, String pEmail, String pLanguage, String pPassword, String pTimeZone) throws AccountNotFoundException, NotAllowedException;

    Account getMyAccount() throws AccountNotFoundException;

    Account checkAdmin(Organization pOrganization) throws AccessRightException, AccountNotFoundException;

    Account checkAdmin(String pOrganizationName) throws AccessRightException, AccountNotFoundException, OrganizationNotFoundException;

    void setGCMAccount(String gcmId) throws AccountNotFoundException, GCMAccountAlreadyExistsException, CreationException, GCMAccountNotFoundException;

    void deleteGCMAccount() throws AccountNotFoundException, GCMAccountNotFoundException;

    boolean isAccountEnabled(String pLogin) throws AccountNotFoundException;

    List<Account> getAccounts();

    Account enableAccount(String login, boolean enabled) throws AccountNotFoundException, NotAllowedException;

    Account authenticateAccount(String login, String password);

    UserGroupMapping getUserGroupMapping(String login);
}
