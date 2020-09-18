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
package com.docdoku.plm.server.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.docdoku.plm.server.core.security.UserGroupMapping;
import com.docdoku.plm.server.rest.file.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@RequestScoped
@Api(value = "files", description = "Operations about files")
@Path("files")
@DeclareRoles({UserGroupMapping.REGULAR_USER_ROLE_ID, UserGroupMapping.GUEST_ROLE_ID})
@RolesAllowed({UserGroupMapping.REGULAR_USER_ROLE_ID, UserGroupMapping.GUEST_ROLE_ID})
public class FileResource {

    @Inject
    private DocumentBinaryResource documentBinaryResource;

    @Inject
    private PartBinaryResource partBinaryResource;

    @Inject
    private DocumentTemplateBinaryResource documentTemplateBinaryResource;

    @Inject
    private PartTemplateBinaryResource partTemplateBinaryResource;

    @Inject
    private ProductInstanceBinaryResource productInstanceBinaryResource;

    public FileResource() {
    }

    @ApiOperation(value = "documents")
    @Path("/{workspaceId}/documents/{documentId}/{version}")
    public DocumentBinaryResource documentFile() {
        return documentBinaryResource;
    }

    @ApiOperation(value = "parts")
    @Path("/{workspaceId}/parts/{partNumber}/{version}")
    public PartBinaryResource partFile() {
        return partBinaryResource;
    }

    @ApiOperation(value = "document-templates")
    @Path("/{workspaceId}/document-templates/{templateId}/")
    @RolesAllowed({UserGroupMapping.REGULAR_USER_ROLE_ID})
    public DocumentTemplateBinaryResource documentTemplateFile() {
        return documentTemplateBinaryResource;
    }

    @ApiOperation(value = "part-templates")
    @Path("/{workspaceId}/part-templates/{templateId}/")
    @RolesAllowed({UserGroupMapping.REGULAR_USER_ROLE_ID})
    public PartTemplateBinaryResource partTemplateFile() {
        return partTemplateBinaryResource;
    }

    @ApiOperation(value = "product-instances")
    @Path("/{workspaceId}/product-instances/{serialNumber}/{ciId}/")
    @RolesAllowed({UserGroupMapping.REGULAR_USER_ROLE_ID})
    public ProductInstanceBinaryResource productInstanceFile() {
        return productInstanceBinaryResource;
    }

}
