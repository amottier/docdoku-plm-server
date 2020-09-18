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

package com.docdoku.plm.server.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value="WorkspaceListDTO", description="This class holds a list of {@link com.docdoku.plm.server.core.common.Workspace} entities")
public class WorkspaceListDTO implements Serializable {

    @ApiModelProperty(value = "Administrated workspace list")
    private List<WorkspaceDTO> administratedWorkspaces = new ArrayList<>();

    @ApiModelProperty(value = "All workspace list")
    private List<WorkspaceDTO> allWorkspaces = new ArrayList<>();

    public WorkspaceListDTO() {
    }

    public void addAdministratedWorkspaces(WorkspaceDTO workspace) {
        this.administratedWorkspaces.add(workspace);
    }

    public List<WorkspaceDTO> getAdministratedWorkspaces() {
        return administratedWorkspaces;
    }

    public void setAdministratedWorkspaces(List<WorkspaceDTO> administratedWorkspaces) {
        this.administratedWorkspaces = administratedWorkspaces;
    }

    public List<WorkspaceDTO> getAllWorkspaces() {
        return allWorkspaces;
    }

    public void setAllWorkspaces(List<WorkspaceDTO> allWorkspaces) {
        this.allWorkspaces = allWorkspaces;
    }

    public void addAllWorkspaces(WorkspaceDTO workspace) {
        this.allWorkspaces.add(workspace);
    }
}
