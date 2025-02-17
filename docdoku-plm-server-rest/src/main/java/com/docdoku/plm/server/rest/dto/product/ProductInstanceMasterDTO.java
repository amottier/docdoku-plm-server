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

package com.docdoku.plm.server.rest.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.docdoku.plm.server.rest.dto.ACLDTO;

import java.io.Serializable;
import java.util.List;


@ApiModel(value="ProductInstanceMasterDTO", description="This class is the representation of {@link com.docdoku.plm.server.core.configuration.ProductInstanceMaster} entity")
public class ProductInstanceMasterDTO implements Serializable {

    @ApiModelProperty(value = "Product instance identifier")
    private String identifier;

    @ApiModelProperty(value = "Product instance serial number")
    private String serialNumber;

    @ApiModelProperty(value = "Configuration item used")
    private String configurationItemId;

    @ApiModelProperty(value = "Product instance iterations")
    private List<ProductInstanceIterationDTO> productInstanceIterations;

    @ApiModelProperty(value = "Product instance ACL")
    private ACLDTO acl;

    public ProductInstanceMasterDTO() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getConfigurationItemId() {
        return configurationItemId;
    }

    public void setConfigurationItemId(String configurationItemId) {
        this.configurationItemId = configurationItemId;
    }

    public List<ProductInstanceIterationDTO> getProductInstanceIterations() {
        return productInstanceIterations;
    }

    public void setProductInstanceIterations(List<ProductInstanceIterationDTO> productInstanceIterations) {
        this.productInstanceIterations = productInstanceIterations;
    }

    public ACLDTO getAcl() {
        return acl;
    }

    public void setAcl(ACLDTO acl) {
        this.acl = acl;
    }

}
