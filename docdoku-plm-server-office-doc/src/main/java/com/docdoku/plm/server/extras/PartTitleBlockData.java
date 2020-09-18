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

import com.docdoku.plm.server.core.product.PartIteration;
import com.docdoku.plm.server.i18n.PropertiesLoader;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * This class should be used to override the default PDF layout applied
 * when generating part.
 *
 * @author Charles Fallourd
 * @version 2.5, 05/01/16
 * @see TitleBlockGenerator
 */
public class PartTitleBlockData extends TitleBlockData {

    PartTitleBlockData(PartIteration partIteration, Locale pLocale) {
        locale = pLocale;
        properties = PropertiesLoader.loadLocalizedProperties(locale, PROPERTIES_BASE_NAME, getClass());
        dateFormat = new SimpleDateFormat(properties.getProperty("date.format"));
        authorName = partIteration.getAuthor().getName();
        version = partIteration.getVersion();
        creationDate = dateFormat.format(partIteration.getPartRevision().getCreationDate());
        iterationDate = dateFormat.format(partIteration.getCreationDate());
        keywords = partIteration.getPartRevision().getTags().toString();
        description = partIteration.getPartRevision().getDescription();
        instanceAttributes = partIteration.getInstanceAttributes();
        currentIteration = String.valueOf(partIteration.getIteration());
        workflow = partIteration.getPartRevision().getWorkflow();
        revisionNote = partIteration.getIterationNote();
        lifeCycleState = partIteration.getPartRevision().getLifeCycleState();
        title = partIteration.getNumber() + "-" + version;
        subject = partIteration.getName();
    }
}
