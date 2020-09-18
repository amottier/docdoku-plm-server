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

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

/**
 * @author Yassine Belouad
 */


@ApiModel(value="TagDTO", description="This class is a representation of a {@link com.docdoku.plm.server.core.meta.Tag} entity")
public class TagDTO implements Serializable {

    @ApiModelProperty(value = "Tag id")
    @JsonbProperty(nillable = true)
    private String id;

    @ApiModelProperty(value = "Tag label")
    @JsonbProperty(nillable = true)
    private String label;

    @ApiModelProperty(value = "Workspace id")
    @JsonbProperty(nillable = true)
    private String workspaceId;

    public TagDTO() {
    }

    public TagDTO(String label) {
        this.label = label;
    }

    public TagDTO(String label, String workspaceId) {
        this.id = label;
        this.label = label;
        this.workspaceId = workspaceId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getId() {
        id = this.label;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
