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

import com.docdoku.plm.server.core.common.User;
import com.docdoku.plm.server.core.document.DocumentIteration;
import com.docdoku.plm.server.core.document.DocumentRevisionKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Baselines could be seen as "snapshots in time" of a set of documents.
 * More concretely, baselines are collections of documents
 * at a given iteration plus various metadata like {@code author},
 * {@code type} or {@code description} for instance.
 *
 * @author Taylor Labejof
 * @version 2.0, 25/08/14
 * @since V2.0
 */
@Table(name="DOCUMENTBASELINE")
@Entity
public class DocumentBaseline implements Serializable {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    private DocumentBaselineType type = DocumentBaselineType.LATEST;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private DocumentCollection documentCollection = new DocumentCollection();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "AUTHOR_LOGIN", referencedColumnName = "LOGIN"),
            @JoinColumn(name = "AUTHOR_WORKSPACE_ID", referencedColumnName = "WORKSPACE_ID")
    })
    private User author;

    public DocumentBaseline() {
    }

    public DocumentBaseline(User author, String name, DocumentBaselineType type, String description) {
        this.author = author;
        this.name = name;
        this.type = type;
        this.description = description;
        this.creationDate = new Date();
    }

    public void addBaselinedDocument(DocumentIteration targetDocument){
        documentCollection.addBaselinedDocument(targetDocument);

    }
    public boolean hasBaselinedDocument(DocumentRevisionKey documentRevisionKey){
        if(documentCollection != null){
            return documentCollection.hasBaselinedDocument(documentRevisionKey);
        }
        return false;
    }
    public BaselinedDocument getBaselinedDocument(DocumentRevisionKey documentRevisionKey){
        return (documentCollection!=null) ? documentCollection.getBaselinedDocument(documentRevisionKey) : null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public DocumentBaselineType getType() {
        return type;
    }

    public void setType(DocumentBaselineType type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return (creationDate!=null) ? (Date) creationDate.clone(): null;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = (Date) creationDate.clone();
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentCollection getDocumentCollection() {
        return documentCollection;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentBaseline)) {
            return false;
        }

        DocumentBaseline baseline = (DocumentBaseline) o;
        return id == baseline.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
