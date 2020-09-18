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

import com.docdoku.plm.server.core.document.DocumentMasterTemplate;
import com.docdoku.plm.server.core.exceptions.CreationException;
import com.docdoku.plm.server.core.exceptions.WorkflowModelAlreadyExistsException;
import com.docdoku.plm.server.core.exceptions.WorkflowModelNotFoundException;
import com.docdoku.plm.server.core.product.PartMasterTemplate;
import com.docdoku.plm.server.core.workflow.ActivityModel;
import com.docdoku.plm.server.core.workflow.TaskModel;
import com.docdoku.plm.server.core.workflow.WorkflowModel;
import com.docdoku.plm.server.core.workflow.WorkflowModelKey;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class WorkflowModelDAO {

    private static final Logger LOGGER = Logger.getLogger(WorkflowModelDAO.class.getName());
    public static final String WORKSPACE_ID = "workspaceId";

    @Inject
    private EntityManager em;

    public WorkflowModelDAO(){
    }

    public void removeAllActivityModels(WorkflowModelKey pKey) {
        em.createQuery("DELETE FROM TaskModel t WHERE t.activityModel.workflowModel.id = :id AND t.activityModel.workflowModel.workspaceId = :workspaceId")
                .setParameter("id", pKey.getId())
                .setParameter(WORKSPACE_ID, pKey.getWorkspaceId()).executeUpdate();
        em.createQuery("DELETE FROM ActivityModel a WHERE a.workflowModel.id = :id AND a.workflowModel.workspaceId = :workspaceId")
                .setParameter("id", pKey.getId())
                .setParameter(WORKSPACE_ID, pKey.getWorkspaceId()).executeUpdate();
    }

    public void removeWorkflowModel(WorkflowModelKey pKey) throws WorkflowModelNotFoundException {
        WorkflowModel model = loadWorkflowModel(pKey);
        for(ActivityModel activity:model.getActivityModels()){
            activity.setRelaunchActivity(null);
        }
        em.flush();
        em.remove(model);
    }

    public List<WorkflowModel> findAllWorkflowModels(String pWorkspaceId) {
        TypedQuery<WorkflowModel> query = em.createQuery("SELECT DISTINCT w FROM WorkflowModel w WHERE w.workspaceId = :workspaceId",WorkflowModel.class);
        return query.setParameter(WORKSPACE_ID, pWorkspaceId).getResultList();
    }

    public void removeWorkflowModelConstraints(WorkflowModel pWorkflowModel){
        if(pWorkflowModel != null) {
            List<ActivityModel> activityModels = pWorkflowModel.getActivityModels();
            for(ActivityModel activityModel:activityModels){
                activityModel.setRelaunchActivity(null);
            }
            em.flush();
        }
    }

    public void createWorkflowModel(WorkflowModel pModel) throws WorkflowModelAlreadyExistsException, CreationException {
        try {
            //the EntityExistsException is thrown only when flush occurs
            //Because ActivityModel has a generated id which is part of the TaskModel's PK
            //we force generated it to avoid cache issue with the TaskModel.
            List<ActivityModel> activityModels = pModel.getActivityModels();
            List<List<TaskModel>> taskModels=new LinkedList<>();
            for(ActivityModel activityModel:activityModels){
                taskModels.add(activityModel.getTaskModels());
                activityModel.setTaskModels(new ArrayList<>());
            }
            em.persist(pModel);
            em.flush();
            int i=0;
            for(ActivityModel activityModel:activityModels){
                activityModel.setTaskModels(taskModels.get(i++));
            }
        } catch (EntityExistsException pEEEx) {
            LOGGER.log(Level.FINEST,null,pEEEx);
            throw new WorkflowModelAlreadyExistsException(pModel);
        } catch (PersistenceException pPEx) {
            LOGGER.log(Level.FINEST,null,pPEx);
            //EntityExistsException is case sensitive
            //whereas MySQL is not thus PersistenceException could be
            //thrown instead of EntityExistsException
            throw new CreationException();
        }
    }

    public WorkflowModel loadWorkflowModel(WorkflowModelKey pKey) throws WorkflowModelNotFoundException {
        WorkflowModel model = em.find(WorkflowModel.class, pKey);
        if (model == null) {
            throw new WorkflowModelNotFoundException(pKey.getId());
        } else {
            return model;
        }
    }

    public boolean isInUseInDocumentMasterTemplate(WorkflowModel workflowModel) {
        return !em.createNamedQuery("DocumentMasterTemplate.findWhereWorkflowModel", DocumentMasterTemplate.class)
                .setParameter("workflowModel",workflowModel)
                .getResultList()
                .isEmpty();
    }

    public boolean isInUseInPartMasterTemplate(WorkflowModel workflowModel) {
        return !em.createNamedQuery("PartMasterTemplate.findWhereWorkflowModel", PartMasterTemplate.class)
                .setParameter("workflowModel",workflowModel)
                .getResultList()
                .isEmpty();
    }
}
