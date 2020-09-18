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


@ApiModel(value="LightPartLinkDTO", description="This class is a light representation of a {@link com.docdoku.plm.server.core.product.PartLink} entity")
public class LightPartLinkDTO implements Serializable {

    @ApiModelProperty(value = "Part number")
    private String number;

    @ApiModelProperty(value = "Part name")
    private String name;

    @ApiModelProperty(value = "Link description")
    private String referenceDescription;

    @ApiModelProperty(value = "Complete component path in context")
    private String fullId;

    public LightPartLinkDTO() {
    }

    public LightPartLinkDTO(String number, String name, String referenceDescription, String fullId) {
        this.number = number;
        this.name = name;
        this.referenceDescription = referenceDescription;
        this.fullId = fullId;
    }
/*

    public LightPartLinkDTO(PartLink partLink) {
        number = partLink.getComponent().getNumber();
        name = partLink.getComponent().getName();
        referenceDescription = partLink.getReferenceDescription();
        fullId = partLink.getFullId();
    }
*/

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceDescription() {
        return referenceDescription;
    }

    public void setReferenceDescription(String referenceDescription) {
        this.referenceDescription = referenceDescription;
    }

    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
    }
}
