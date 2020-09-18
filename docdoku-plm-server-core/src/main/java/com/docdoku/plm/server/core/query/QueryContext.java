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

package com.docdoku.plm.server.core.query;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A QueryContext is the scope on which a {@link Query} is executed. So it is
 * a constituent part of a query.
 * It determines if the query will fetch parts from the whole catalog,
 * a specific product or more precisely from a deliverable instance.
 *
 * @author Morgan Guimard
 */
@Table(name="QUERYCONTEXT")
@Entity
public class QueryContext implements Serializable{

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    private String workspaceId;

    private String serialNumber;

    private String configurationItemId;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="QUERY_ID", referencedColumnName="ID"),

    })
    private Query parentQuery;

    public QueryContext() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfigurationItemId() {
        return configurationItemId;
    }

    public void setConfigurationItemId(String configurationItemId) {
        this.configurationItemId = configurationItemId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Query getParentQuery() {
        return parentQuery;
    }

    public void setParentQuery(Query parentQuery) {
        this.parentQuery = parentQuery;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
