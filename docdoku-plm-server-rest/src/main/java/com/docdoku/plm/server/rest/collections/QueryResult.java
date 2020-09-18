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

package com.docdoku.plm.server.rest.collections;

import com.docdoku.plm.server.core.product.PartRevision;
import com.docdoku.plm.server.core.query.Query;
import com.docdoku.plm.server.core.query.QueryResultRow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Morgan Guimard
 */

public class QueryResult {

    private Query query;
    private List<QueryResultRow> rows = new ArrayList<>();

    private ExportType exportType = ExportType.JSON;

    public QueryResult() {
    }

    public QueryResult(Query query, List<QueryResultRow> rows) {
        this.query = query;
        this.rows = rows;
    }

    public QueryResult(List<PartRevision> partRevisions, Query query) {
        this.query = query;
        for (PartRevision partRevision : partRevisions) {
            rows.add(new QueryResultRow(partRevision));
        }
    }

    public ExportType getExportType() {
        return exportType;
    }

    public void setExportType(ExportType exportType) {
        this.exportType = exportType;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<QueryResultRow> getRows() {
        return rows;
    }

    public void setRows(List<QueryResultRow> rows) {
        this.rows = rows;
    }

    public void mergeRows(List<QueryResultRow> rows) {
        List<QueryResultRow> mergedRows = new ArrayList<>();
        if (rows != null && !rows.isEmpty()) {
            for (QueryResultRow row : rows) {
                for (QueryResultRow filteredRow : this.rows) {
                    if (filteredRow.getPartRevision().equals(row.getPartRevision())) {
                        mergedRows.add(row);
                        break;
                    }
                }
            }
        }
        this.rows = mergedRows;
    }

    public enum ExportType {
        JSON, CSV, XLS
    }


}
