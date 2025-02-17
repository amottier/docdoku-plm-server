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
import java.util.List;


@ApiModel(value = "WorkspaceFrontOptionsDTO",
        description = "This class is the representation of a {@link com.docdoku.plm.server.core.admin.WorkspaceFrontOptions} entity"
)
public class WorkspaceFrontOptionsDTO implements Serializable {

    @ApiModelProperty(value = "Part table columns")
    private List<String> partTableColumns;

    @ApiModelProperty(value = "Document table columns")
    private List<String> documentTableColumns;

    public WorkspaceFrontOptionsDTO() {
    }

    public List<String> getPartTableColumns() {
        return partTableColumns;
    }

    public void setPartTableColumns(List<String> partTableColumns) {
        this.partTableColumns = partTableColumns;
    }

    public List<String> getDocumentTableColumns() {
        return documentTableColumns;
    }

    public void setDocumentTableColumns(List<String> documentTableColumns) {
        this.documentTableColumns = documentTableColumns;
    }
}
