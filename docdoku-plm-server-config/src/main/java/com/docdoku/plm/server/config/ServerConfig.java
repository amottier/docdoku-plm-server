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

package com.docdoku.plm.server.config;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import java.util.Optional;
import java.util.Properties;

/**
 * Get config from resources
 *
 * @author Morgan Guimard
 */
@Singleton
public class ServerConfig {

    @Resource(lookup="docdokuplm.config")
    private Properties properties;

    public String getCodebase(){
        return properties.getProperty("codebase");
    }

    public String getVaultPath(){
        return properties.getProperty("vaultPath");
    }

    public String getConversionsPath(){
        return properties.getProperty("conversionsPath");
    }

    public String getDigestAlgorithm() {
        return Optional.ofNullable(properties.getProperty("digestAlgorithm")).orElse("MD5");
    }

}
