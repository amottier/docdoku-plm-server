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
package com.docdoku.plm.server.core.services;

import com.docdoku.plm.server.core.common.BinaryResource;
import com.docdoku.plm.server.core.document.DocumentIterationKey;
import com.docdoku.plm.server.core.document.DocumentRevision;
import com.docdoku.plm.server.core.document.DocumentRevisionKey;
import com.docdoku.plm.server.core.exceptions.DocumentRevisionNotFoundException;
import com.docdoku.plm.server.core.exceptions.FileNotFoundException;
import com.docdoku.plm.server.core.exceptions.PartRevisionNotFoundException;
import com.docdoku.plm.server.core.product.PartIterationKey;
import com.docdoku.plm.server.core.product.PartRevision;
import com.docdoku.plm.server.core.product.PartRevisionKey;

/**
 *
 * @author Morgan Guimard
 */
public interface IPublicEntityManagerLocal {
    PartRevision getPublicPartRevision(PartRevisionKey partRevisionKey);
    DocumentRevision getPublicDocumentRevision(DocumentRevisionKey documentRevisionKey);
    BinaryResource getPublicBinaryResourceForDocument(String fullName) throws FileNotFoundException;
    BinaryResource getPublicBinaryResourceForPart(String fileName) throws FileNotFoundException;
    BinaryResource getBinaryResourceForSharedEntity(String fileName) throws FileNotFoundException;
    boolean canAccess(PartIterationKey partIKey) throws PartRevisionNotFoundException;
    boolean canAccess(DocumentIterationKey partIKey) throws DocumentRevisionNotFoundException;
    BinaryResource getBinaryResourceForProductInstance(String fullName) throws FileNotFoundException;
    BinaryResource getBinaryResourceForPathData(String fullName) throws FileNotFoundException;
}
