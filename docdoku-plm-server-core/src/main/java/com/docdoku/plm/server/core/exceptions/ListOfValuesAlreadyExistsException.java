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

package com.docdoku.plm.server.core.exceptions;

import com.docdoku.plm.server.core.meta.ListOfValues;

import java.text.MessageFormat;


/**
 *
 * @author Florent Garin
 */
public class ListOfValuesAlreadyExistsException extends EntityAlreadyExistsException {
    private final ListOfValues mLov;


    public ListOfValuesAlreadyExistsException(String pMessage) {
        super(pMessage);
        mLov=null;
    }

    public ListOfValuesAlreadyExistsException(ListOfValues pLov) {
        this(pLov, null);
    }

    public ListOfValuesAlreadyExistsException(ListOfValues pLov, Throwable pCause) {
        super( pCause);
        mLov=pLov;
    }

    @Override
    public String getLocalizedMessage() {
        String message = getBundleDefaultMessage();
        return MessageFormat.format(message,mLov);
    }
}
