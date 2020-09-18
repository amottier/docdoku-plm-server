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

package com.docdoku.plm.server.core.configuration;

import com.docdoku.plm.server.core.product.PartIteration;
import com.docdoku.plm.server.core.product.PartLink;

import java.io.Serializable;

/**
 * Class that wraps a {@link PartLink} with the correct version
 * of {@link PartIteration} that has been selected according to a config spec.
 *
 * Instances of this class are not persisted.
 *
 * @author Morgan Guimard
 * @version 2.0, 07/21/15
 * @since 2.0
 */
public class ResolvedPartLink implements Serializable {

    private PartIteration partIteration;
    private PartLink partLink;

    public ResolvedPartLink(PartIteration partIteration, PartLink partLink) {
        this.partIteration = partIteration;
        this.partLink = partLink;
    }

    public PartIteration getPartIteration() {
        return partIteration;
    }

    public void setPartIteration(PartIteration partIteration) {
        this.partIteration = partIteration;
    }

    public PartLink getPartLink() {
        return partLink;
    }

    public void setPartLink(PartLink partLink) {
        this.partLink = partLink;
    }
}
