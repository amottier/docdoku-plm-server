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

package com.docdoku.plm.server.dao;

import com.docdoku.plm.server.core.common.Account;
import com.docdoku.plm.server.core.common.OAuthProvider;
import com.docdoku.plm.server.core.common.ProvidedAccount;
import com.docdoku.plm.server.core.exceptions.OAuthProviderNotFoundException;
import com.docdoku.plm.server.core.exceptions.ProvidedAccountNotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


@RequestScoped
public class OAuthProviderDAO {

    @Inject
    private EntityManager em;

    public OAuthProviderDAO() {
    }

    public OAuthProvider findProvider(int pId) throws OAuthProviderNotFoundException {
        OAuthProvider oAuthProvider = em.find(OAuthProvider.class, pId);
        if (oAuthProvider == null) {
            throw new OAuthProviderNotFoundException(pId);
        }
        return oAuthProvider;
    }

    public void createProvider(OAuthProvider oAuthProvider) {
        // try catch needed ?
        em.persist(oAuthProvider);
        em.flush();
    }

    public void removeProvider(int id) throws OAuthProviderNotFoundException {
        OAuthProvider provider = findProvider(id);
        em.remove(provider);
        em.flush();
    }

    public List<OAuthProvider> getProviders() {
        return em.createNamedQuery("OAuthProvider.findAll", OAuthProvider.class).getResultList();
    }


    public ProvidedAccount findProvidedAccount(int id, String sub) throws ProvidedAccountNotFoundException {
        try {
            return em.createNamedQuery("ProvidedAccount.getProvidedAccount", ProvidedAccount.class)
                    .setParameter("id", id)
                    .setParameter("sub", sub)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ProvidedAccountNotFoundException(sub);
        }
    }

    public ProvidedAccount findProvidedAccount(Account account) throws ProvidedAccountNotFoundException {
        try {
            return em.createNamedQuery("ProvidedAccount.getProvidedAccountFromAccount", ProvidedAccount.class)
                    .setParameter("account", account)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ProvidedAccountNotFoundException(account.getLogin());
        }
    }

    public boolean hasProvidedAccount(Account account) {
        try {
            findProvidedAccount(account);
            return true;
        } catch (ProvidedAccountNotFoundException e) {
            return false;
        }
    }

}
