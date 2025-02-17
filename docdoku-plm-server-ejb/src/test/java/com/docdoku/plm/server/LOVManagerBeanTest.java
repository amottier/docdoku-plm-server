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

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.docdoku.plm.server.core.common.Account;
import com.docdoku.plm.server.core.common.User;
import com.docdoku.plm.server.core.common.Workspace;
import com.docdoku.plm.server.core.exceptions.CreationException;
import com.docdoku.plm.server.core.meta.NameValuePair;
import com.docdoku.plm.server.core.services.IUserManagerLocal;
import com.docdoku.plm.server.dao.LOVDAO;
import com.docdoku.plm.server.dao.WorkspaceDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author lebeaujulien on 12/03/15.
 */
public class LOVManagerBeanTest {

    private static final String WORKSPACE_ID="TestWorkspace";
    private static final String USER_LOGIN="User1";
    private static final String USER_NAME="UserName";
    private static final String USER_MAIL="UserMail@mail.com";
    private static final String USER_LANGUAGE="fr";

    @InjectMocks
    private LOVManagerBean lovManager;

    @Mock
    private LOVDAO lovDAO;

    @Mock
    private WorkspaceDAO workspaceDAO;

    @Mock
    private IUserManagerLocal userManager;

    private Workspace workspace ;
    private User user;

    @Before
    public void setup() {
        initMocks(this);
        Account account = new Account(USER_LOGIN, USER_NAME, USER_MAIL, USER_LANGUAGE, new Date(), null);
        workspace = new Workspace(WORKSPACE_ID, account, "pDescription", false);
        user = new User(workspace, new Account(USER_LOGIN, USER_LOGIN, USER_MAIL,USER_LANGUAGE, new Date(), null));
    }

    @Test
    public void creationNominal() throws Exception {
        Mockito.when(userManager.checkWorkspaceWriteAccess(workspace.getId())).thenReturn(user);
        Mockito.when(userManager.checkWorkspaceReadAccess(workspace.getId())).thenReturn(user);
        Mockito.when(workspaceDAO.loadWorkspace(workspace.getId())).thenReturn(workspace);

        List<NameValuePair> possibleValue = new ArrayList<>();
        possibleValue.add(new NameValuePair("first", "value"));

        lovManager.createLov(workspace.getId(), "NominalName", possibleValue);
    }

    @Test (expected = CreationException.class)
    public void creationNoName() throws Exception {
        Mockito.when(userManager.checkWorkspaceWriteAccess(workspace.getId())).thenReturn(user);
        Mockito.when(userManager.checkWorkspaceReadAccess(workspace.getId())).thenReturn(user);

        List<NameValuePair> possibleValue = new ArrayList<>();
        possibleValue.add(new NameValuePair("first", "value"));

        lovManager.createLov(workspace.getId(), null, possibleValue);
    }

    @Test (expected = CreationException.class)
    public void creationNoPossibleValue() throws Exception {
        Mockito.when(userManager.checkWorkspaceWriteAccess(workspace.getId())).thenReturn(user);
        Mockito.when(userManager.checkWorkspaceReadAccess(workspace.getId())).thenReturn(user);

        lovManager.createLov(workspace.getId(), "Name", null);
    }
}
