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

import com.docdoku.plm.server.core.admin.OperationSecurityStrategy;
import com.docdoku.plm.server.core.admin.PlatformOptions;

/**
 *
 * @author Morgan Guimard
 * @version 2.5, 03/02/16
 * @since   V2.5
 */
public interface IPlatformOptionsManagerLocal {
    OperationSecurityStrategy getWorkspaceCreationStrategy();
    OperationSecurityStrategy getRegistrationStrategy();
    void setWorkspaceCreationStrategy(OperationSecurityStrategy strategy);
    void setRegistrationStrategy(OperationSecurityStrategy strategy);
    PlatformOptions getPlatformOptions();
}
