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

package com.docdoku.plm.server.core.security;

import com.docdoku.plm.server.core.util.HashUtils;

import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Useful class for storing credential, login/password pair, to the persistence
 * storage. 
 * 
 * @author Florent Garin
 * @version 1.0, 02/06/08
 * @since   V1.0
 */
@Table(name="CREDENTIAL")
@javax.persistence.Entity
@NamedQuery(name="Credential.authenticate", query = "SELECT c FROM Credential c WHERE :login = c.login AND :password = c.password")
public class Credential implements java.io.Serializable {


    @javax.persistence.Id
    private String login="";
    
    private String password;

    private static final Logger LOGGER = Logger.getLogger(Credential.class.getName());

    public Credential() {
    }
    
    public static Credential createCredential(String pLogin, String pClearPassword, String pAlgorithm){
        Credential credential = new Credential();
        credential.login = pLogin;
        try {
            credential.password= HashUtils.digest(pClearPassword, pAlgorithm);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException pEx) {
            LOGGER.log(Level.SEVERE, null, pEx);
        }
        return credential;
    }

    public boolean authenticate(String pPassword, String pAlgorithm){
        try {
            return password != null && pPassword != null && HashUtils.digest(pPassword, pAlgorithm).equals(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException pEx) {
            LOGGER.log(Level.SEVERE, null, pEx);
            return false;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
