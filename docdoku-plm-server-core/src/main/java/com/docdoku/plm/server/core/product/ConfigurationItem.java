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

import com.docdoku.plm.server.core.common.User;
import com.docdoku.plm.server.core.common.Workspace;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an entire product or some portion
 * than is planned for production.
 * Configuration management is done using this class that controls
 * the composition of constituents for actual units of production.
 * 
 * All the effectivities that reference a given ConfigurationItem
 * must be of the same type.
 * Application logic should insure it is not possible to mix effectivity
 * types for the same configuration item.
 * 
 * @author Florent Garin
 * @version 1.1, 31/10/11
 * @since   V1.1
 */
@Table(name="CONFIGURATIONITEM")
@NamedQueries({
        @NamedQuery(name="ConfigurationItem.getConfigurationItemsInWorkspace",query="SELECT DISTINCT ci FROM ConfigurationItem ci WHERE ci.workspace.id = :workspaceId"),
        @NamedQuery(name="ConfigurationItem.getEffectivities",query="SELECT e FROM Effectivity e WHERE e.configurationItem = :configurationItem"),
        @NamedQuery(name="ConfigurationItem.findByDesignItem",query="SELECT c FROM ConfigurationItem c WHERE c.designItem = :designItem"),
        @NamedQuery(name="ConfigurationItem.findByPathToPathLink",query="SELECT c FROM ConfigurationItem c WHERE :pathToPathLink member of c.pathToPathLinks")
})
@javax.persistence.IdClass(com.docdoku.plm.server.core.product.ConfigurationItemKey.class)
@Entity
public class ConfigurationItem implements Serializable {

    @Column(length = 100)
    @Id
    private String id = "";
    
    @Id
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Workspace workspace;


    @Lob
    private String description;
       
    /**
     * The top level of the design of the configuration item
     * which is the context for effectivities.
     */
    @ManyToOne(fetch= FetchType.LAZY, optional=false)
    @JoinColumns({
        @JoinColumn(name = "PARTMASTER_PARTNUMBER", referencedColumnName = "PARTNUMBER"),
        @JoinColumn(name = "PARTMASTER_WORKSPACE_ID", referencedColumnName = "WORKSPACE_ID")
    })
    private PartMaster designItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "AUTHOR_LOGIN", referencedColumnName = "LOGIN"),
            @JoinColumn(name = "AUTHOR_WORKSPACE_ID", referencedColumnName = "WORKSPACE_ID")
    })
    private User author;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CONFIGURATIONITEM_P2PLINK",
            inverseJoinColumns = {
                    @JoinColumn(name = "PATHTOPATHLINK_ID", referencedColumnName = "ID")
            },
            joinColumns = {
                    @JoinColumn(name="CONFIGURATIONITEM_ID", referencedColumnName="ID"),
                    @JoinColumn(name="WORKSPACE_ID", referencedColumnName="WORKSPACE_ID")
            })
    private List<PathToPathLink> pathToPathLinks =new ArrayList<>();

    public ConfigurationItem() {
    }

    public ConfigurationItem(User author,Workspace pWorkspace, String pId, String pDescription) {
        this.author = author;
        this.workspace=pWorkspace;
        this.id=pId;
        this.description=pDescription;
    }
    
    public PartMaster getDesignItem() {
        return designItem;
    }

    public void setDesignItem(PartMaster designItem) {
        this.designItem = designItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Workspace getWorkspace() {
        return workspace;
    }
    
    public String getWorkspaceId() {
        return workspace == null ? "" : workspace.getId();
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public ConfigurationItemKey getKey(){
        return new ConfigurationItemKey(getWorkspaceId(),getId());
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<PathToPathLink> getPathToPathLinks() {
        return pathToPathLinks;
    }

    public void setPathToPathLinks(List<PathToPathLink> pathToPathLinks) {
        this.pathToPathLinks = pathToPathLinks;
    }

    public void addPathToPathLink(PathToPathLink pathToPathLink) {
        pathToPathLinks.add(pathToPathLink);
    }

    public void removePathToPathLink(PathToPathLink pathToPathLink) {
        pathToPathLinks.remove(pathToPathLink);
    }

    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        if (!(pObj instanceof ConfigurationItem)) {
            return false;
        }
        ConfigurationItem ci = (ConfigurationItem) pObj;
        return ci.id.equals(id) && ci.getWorkspaceId().equals(getWorkspaceId());
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + getWorkspaceId().hashCode();
        hash = 31 * hash + id.hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        return id;
    }
    
    
}
