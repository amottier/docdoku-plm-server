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

/**
 * @author Morgan Guimard
 */


@ApiModel(value="RoleMappingDTO", description="Use this class to provide roles mapping for {@link com.docdoku.plm.server.core.workflow.Workflow} entity instantiation")
public class RoleMappingDTO implements Serializable {

    @ApiModelProperty(value = "Role name")
    private String roleName;

    @ApiModelProperty(value = "Mapped users")
    private List<String> userLogins = new ArrayList<>();

    @ApiModelProperty(value = "Mapped groups")
    private List<String> groupIds = new ArrayList<>();

    public RoleMappingDTO() {
    }

    public RoleMappingDTO(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getUserLogins() {
        return userLogins;
    }

    public void setUserLogins(List<String> userLogins) {
        this.userLogins = userLogins;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }
}
