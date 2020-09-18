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


import com.docdoku.plm.server.core.common.User;
import com.docdoku.plm.server.core.common.UserGroup;
import com.docdoku.plm.server.core.document.DocumentRevision;
import com.docdoku.plm.server.core.exceptions.*;
import com.docdoku.plm.server.core.meta.Tag;
import com.docdoku.plm.server.core.product.PartRevision;
import com.docdoku.plm.server.core.services.INotificationManagerLocal;
import com.docdoku.plm.server.core.services.INotifierLocal;
import com.docdoku.plm.server.events.*;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * @author Florent Garin
 */
@Named
@RequestScoped
public class SubscriptionManager {

    @Inject
    private INotificationManagerLocal notificationService;

    @Inject
    private INotifierLocal mailer;

    private void onRemoveTag(@Observes @Removed TagEvent event) throws UserNotFoundException, AccessRightException, UserNotActiveException, TagNotFoundException, WorkspaceNotFoundException, WorkspaceNotEnabledException {
        Tag tag = event.getObservedTag();
        notificationService.removeAllTagSubscriptions(tag.getWorkspaceId(),tag.getLabel());
    }

    private void onRemoveUser(@Observes @Removed UserEvent event) throws UserNotFoundException, WorkspaceNotFoundException, UserNotActiveException, AccessRightException, WorkspaceNotEnabledException {
        User user=event.getObservedUser();
        notificationService.removeAllSubscriptions(user.getWorkspaceId(), user.getLogin());
        notificationService.removeAllTagUserSubscriptions(user.getWorkspaceId(), user.getLogin());
    }

    private void onRemoveUserGroup(@Observes @Removed UserGroupEvent event) throws UserNotFoundException, WorkspaceNotFoundException, UserNotActiveException, AccessRightException, UserGroupNotFoundException, WorkspaceNotEnabledException {
        UserGroup group=event.getObservedUserGroup();
        notificationService.removeAllTagUserGroupSubscriptions(group.getWorkspaceId(), group.getId());
    }

    private void onTagItem(@Observes @Tagged TagEvent event){
        Tag t = event.getObservedTag();
        Collection<User> subscribers = notificationService.getSubscribersForTag(t.getWorkspaceId(), t.getLabel());
        DocumentRevision doc =  event.getTaggableDocument();
        PartRevision part = event.getTaggablePart();
        if(doc !=null)
            mailer.sendTaggedNotification(doc.getWorkspaceId(), subscribers, doc, event.getObservedTag());
        else if(part !=null)
            mailer.sendTaggedNotification(part.getWorkspaceId(), subscribers, part, event.getObservedTag());
    }

    private void onUntagItem(@Observes @Untagged TagEvent event){
        Tag t = event.getObservedTag();
        Collection<User> subscribers = notificationService.getSubscribersForTag(t.getWorkspaceId(),t.getLabel());
        DocumentRevision doc =  event.getTaggableDocument();
        PartRevision part = event.getTaggablePart();
        if(doc !=null)
            mailer.sendUntaggedNotification(doc.getWorkspaceId(), subscribers, doc, event.getObservedTag());
        else if(part != null)
            mailer.sendUntaggedNotification(part.getWorkspaceId(), subscribers, part, event.getObservedTag());
    }


}
