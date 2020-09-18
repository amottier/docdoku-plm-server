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

import io.swagger.annotations.*;
import com.docdoku.plm.server.core.exceptions.AccessRightException;
import com.docdoku.plm.server.core.exceptions.EntityNotFoundException;
import com.docdoku.plm.server.core.exceptions.WorkspaceNotEnabledException;
import com.docdoku.plm.server.core.security.UserGroupMapping;
import com.docdoku.plm.server.core.services.IProductManagerLocal;
import com.docdoku.plm.server.rest.dto.ModificationNotificationDTO;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Florent Garin
 */

@RequestScoped
@Api(hidden = true, value = "modificationNotifications", description = "Operations about modification notifications",
        authorizations = {@Authorization(value = "authorization")})
@DeclareRoles(UserGroupMapping.REGULAR_USER_ROLE_ID)
@RolesAllowed(UserGroupMapping.REGULAR_USER_ROLE_ID)
public class ModificationNotificationResource {

    @Inject
    private IProductManagerLocal productService;

    public ModificationNotificationResource() {
    }

    @PUT
    @ApiOperation(value = "Acknowledge a modification notification",
            response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successful acknowledge of ModificationNotification"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Path("/{notificationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acknowledgeNotification(
            @ApiParam(required = true, value = "Workspace id") @PathParam("workspaceId") String workspaceId,
            @ApiParam(required = true, value = "Notification id") @PathParam("notificationId") int notificationId,
            @ApiParam(required = true, value = "Modification notification to acknowledge") ModificationNotificationDTO notificationDTO)
            throws EntityNotFoundException, AccessRightException, WorkspaceNotEnabledException {

        productService.updateModificationNotification(workspaceId, notificationId, notificationDTO.getAckComment());
        return Response.noContent().build();
    }


}
