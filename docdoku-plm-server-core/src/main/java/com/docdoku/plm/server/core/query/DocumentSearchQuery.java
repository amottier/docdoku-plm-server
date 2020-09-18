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

import java.util.Date;

/**
 * Wraps data needed to perform a basic query on document revisions.
 * This class is not persisted and should be considered as value object.
 *
 * @author Florent Garin
 * @version 2.0, 03/01/2014
 * @since V2.0
 */
public class DocumentSearchQuery extends SearchQuery {
    private String docMId;
    private String title;
    private String folder;

    public DocumentSearchQuery() {

    }

    public DocumentSearchQuery(String workspaceId, String queryString, String docMId, String title, String version, String author, String type, Date creationDateFrom, Date creationDateTo, Date modificationDateFrom, Date modificationDateTo, SearchQuery.AbstractAttributeQuery[] attributes, String[] tags, String content, String folder, boolean fetchHeadOnly) {
        super(workspaceId, queryString, version, author, type, creationDateFrom, creationDateTo, modificationDateFrom, modificationDateTo, attributes, tags, content, fetchHeadOnly);
        this.docMId = docMId;
        this.title = title;
        this.content = content;
        this.folder = folder;
    }

    public String getDocMId() {
        return docMId;
    }

    public void setDocMId(String docMId) {
        this.docMId = docMId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
