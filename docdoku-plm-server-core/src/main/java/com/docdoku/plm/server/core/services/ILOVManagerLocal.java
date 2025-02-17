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

import com.docdoku.plm.server.core.exceptions.*;
import com.docdoku.plm.server.core.meta.ListOfValues;
import com.docdoku.plm.server.core.meta.ListOfValuesKey;
import com.docdoku.plm.server.core.meta.NameValuePair;

import java.util.List;

/**
 * @author Julien Lebeau
 */
public interface ILOVManagerLocal {

    List<ListOfValues> findLOVFromWorkspace(String workspaceId) throws UserNotFoundException, UserNotActiveException, WorkspaceNotFoundException, WorkspaceNotEnabledException;

    ListOfValues findLov(ListOfValuesKey lovKey) throws ListOfValuesNotFoundException, UserNotFoundException, UserNotActiveException, WorkspaceNotFoundException, WorkspaceNotEnabledException;

    void createLov(String workspaceId, String name, List<NameValuePair> nameValuePairList) throws ListOfValuesAlreadyExistsException, CreationException, UserNotFoundException, UserNotActiveException, WorkspaceNotFoundException, AccessRightException, WorkspaceNotEnabledException;

    void deleteLov(ListOfValuesKey lovKey) throws ListOfValuesNotFoundException, UserNotFoundException, UserNotActiveException, WorkspaceNotFoundException, AccessRightException, EntityConstraintException, WorkspaceNotEnabledException;

    ListOfValues updateLov(ListOfValuesKey lovKey, String name, String workspaceId, List<NameValuePair> nameValuePairList) throws ListOfValuesAlreadyExistsException, CreationException, ListOfValuesNotFoundException, UserNotFoundException, WorkspaceNotFoundException, UserNotActiveException, AccessRightException, WorkspaceNotEnabledException;

    boolean isLOVDeletable(ListOfValuesKey lovKey);
}
