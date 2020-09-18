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

package com.docdoku.plm.server.core.meta;

import com.docdoku.plm.server.core.common.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Embeddable class to track status change.
 * Actually it gathers an author {@code statusChangeAuthor}
 * and a date {@code statusModificationDate} attributes.
 *
 * @author Charles Fallourd
 * @version 2.5, 27/02/15
 * @since V2.5
 */
@Embeddable
public class StatusChange implements Serializable{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="USER_LOGIN", referencedColumnName = "LOGIN"),
            @JoinColumn(name="USER_WORKSPACE",referencedColumnName = "WORKSPACE_ID")})
    private User statusChangeAuthor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date statusModificationDate;

    public Date getStatusModificationDate() {
        return statusModificationDate;
    }

    public void setStatusModificationDate(Date statusModificationDate) {
        this.statusModificationDate = statusModificationDate;
    }

    public User getStatusChangeAuthor() {
        return statusChangeAuthor;
    }

    public void setStatusChangeAuthor(User statusChangeAuthor) {
        this.statusChangeAuthor = statusChangeAuthor;
    }

}
