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
package com.docdoku.plm.server.rest.dto.change;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Morgan Guimard
 */


@ApiModel(value="ChangeIssueListDTO", description="This class holds a list of {@link com.docdoku.plm.server.core.change.ChangeIssue} entities")
public class ChangeIssueListDTO implements Serializable {

    @ApiModelProperty(value = "The list of issues")
    private List<ChangeIssueDTO> issues;

    public ChangeIssueListDTO() {
    }

    public List<ChangeIssueDTO> getIssues() {
        return issues;
    }

    public void setIssues(List<ChangeIssueDTO> issues) {
        this.issues = issues;
    }
}
