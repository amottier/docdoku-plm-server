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
package com.docdoku.plm.server.extras;

import com.docdoku.plm.server.core.document.DocumentIteration;
import com.docdoku.plm.server.i18n.PropertiesLoader;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This class should be used to override the default PDF layout applied
 * when generating document.
 *
 * @author Charles Fallourd
 * @version 2.5, 05/01/16
 * @see TitleBlockGenerator
 */
class DocumentTitleBlockData extends TitleBlockData {

    DocumentTitleBlockData(DocumentIteration documentIteration, Locale pLocale) {
        locale = pLocale;
        properties = PropertiesLoader.loadLocalizedProperties(locale, PROPERTIES_BASE_NAME, getClass());
        dateFormat = new SimpleDateFormat(properties.getProperty("date.format"));
        authorName = documentIteration.getAuthor().getName();
        version = documentIteration.getVersion();
        creationDate = dateFormat.format(documentIteration.getDocumentRevision().getCreationDate());
        iterationDate = dateFormat.format(documentIteration.getCreationDate());
        keywords = documentIteration.getDocumentRevision().getTags().toString();
        description = documentIteration.getDocumentRevision().getDescription();
        instanceAttributes = documentIteration.getInstanceAttributes();
        currentIteration = String.valueOf(documentIteration.getIteration());
        workflow = documentIteration.getDocumentRevision().getWorkflow();
        revisionNote = documentIteration.getRevisionNote();
        lifeCycleState = documentIteration.getDocumentRevision().getLifeCycleState();
        title = documentIteration.getId() + "-" + version;
        subject = documentIteration.getTitle();
    }

}
