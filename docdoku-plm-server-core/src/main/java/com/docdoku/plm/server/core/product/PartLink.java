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
package com.docdoku.plm.server.core.product;

import java.util.List;

/**
 * Represents an association between two parts
 * inside a Product Breakdown Structure.
 *
 * @author Morgan Guimard
 * @version 2.5, 26/04/15
 * @see PartUsageLink
 * @see PartSubstituteLink
 * @since   V2.5
 */
public interface PartLink {
    int getId();
    Character getCode();
    String getFullId();
    double getAmount();
    String getUnit();
    String getComment();
    boolean isOptional();
    PartMaster getComponent();
    List<PartSubstituteLink> getSubstitutes();
    String getReferenceDescription();
    List<CADInstance> getCadInstances();
}
