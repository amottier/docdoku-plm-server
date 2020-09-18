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
package com.docdoku.plm.server.listeners.notifications;


import com.docdoku.plm.server.core.exceptions.*;
import com.docdoku.plm.server.core.product.PartIteration;
import com.docdoku.plm.server.core.product.PartRevision;
import com.docdoku.plm.server.core.services.IProductManagerLocal;
import com.docdoku.plm.server.events.CheckedIn;
import com.docdoku.plm.server.events.PartIterationEvent;
import com.docdoku.plm.server.events.PartRevisionEvent;
import com.docdoku.plm.server.events.Removed;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Florent Garin
 */
@Named
@RequestScoped
public class PartNotificationManager {

    @Inject
    private IProductManagerLocal productService;

    private void onRemovePartIteration(@Observes @Removed PartIterationEvent event){
        PartIteration partIteration = event.getObservedPart();
        productService.removeModificationNotificationsOnIteration(partIteration.getKey());
    }

    private void onRemovePartRevision(@Observes @Removed PartRevisionEvent event){
        PartRevision partRevision = event.getObservedPart();
        productService.removeModificationNotificationsOnRevision(partRevision.getKey());
    }
    private void onCheckInPartIteration(@Observes @CheckedIn PartIterationEvent event) throws UserNotFoundException, WorkspaceNotFoundException, UserNotActiveException, PartRevisionNotFoundException, AccessRightException, WorkspaceNotEnabledException {
        PartIteration partIteration = event.getObservedPart();
        productService.createModificationNotifications(partIteration);
    }
}
