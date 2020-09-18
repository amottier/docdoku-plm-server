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

package com.docdoku.plm.server.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.docdoku.plm.server.core.util.DateUtils;

import javax.json.bind.annotation.JsonbDateFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApiModel(value="TaskDTO", description="This class is a representation of a {@link com.docdoku.plm.server.core.workflow.Task} entity")
public class TaskDTO implements Serializable {

    @ApiModelProperty(value = "Task closure comment")
    private String closureComment;

    @ApiModelProperty(value = "Task title")
    private String title;

    @ApiModelProperty(value = "Task instructions")
    private String instructions;

    @ApiModelProperty(value = "Task entity target iteration")
    private int targetIteration;

    @ApiModelProperty(value = "Task closure date")
    @JsonbDateFormat(value = DateUtils.GLOBAL_DATE_FORMAT)
    private Date closureDate;

    @ApiModelProperty(value = "Task signature")
    private String signature;

    @ApiModelProperty(value = "Task assigned users")
    private List<UserDTO> assignedUsers = new ArrayList<>();

    @ApiModelProperty(value = "Task assigned groups")
    private List<UserGroupDTO> assignedGroups = new ArrayList<>();

    @ApiModelProperty(value = "Task effective worker")
    private UserDTO worker;

    @ApiModelProperty(value = "Task status")
    private TaskStatus status;

    @ApiModelProperty(value = "Workspace id")
    private String workspaceId;

    @ApiModelProperty(value = "Task parent workflow")
    private int workflowId;

    @ApiModelProperty(value = "Task parent activity step")
    private int activityStep;

    @ApiModelProperty(value = "Task num")
    private int num;

    @ApiModelProperty(value = "Task holder type")
    private String holderType;

    @ApiModelProperty(value = "Task holder reference")
    private String holderReference;

    @ApiModelProperty(value = "Task holder version")
    private String holderVersion;

    public TaskDTO() {
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getClosureComment() {
        return closureComment;
    }

    public void setClosureComment(String closureComment) {
        this.closureComment = closureComment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getTargetIteration() {
        return targetIteration;
    }

    public void setTargetIteration(int targetIteration) {
        this.targetIteration = targetIteration;
    }

    public Date getClosureDate() {
        return (closureDate != null) ? (Date) closureDate.clone() : null;
    }

    public void setClosureDate(Date date) {
        this.closureDate = (date != null) ? (Date) date.clone() : null;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserDTO getWorker() {
        return worker;
    }

    public void setWorker(UserDTO worker) {
        this.worker = worker;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public int getActivityStep() {
        return activityStep;
    }

    public void setActivityStep(int activityStep) {
        this.activityStep = activityStep;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getHolderReference() {
        return holderReference;
    }

    public void setHolderReference(String holderReference) {
        this.holderReference = holderReference;
    }

    public String getHolderVersion() {
        return holderVersion;
    }

    public void setHolderVersion(String holderVersion) {
        this.holderVersion = holderVersion;
    }

    public List<UserDTO> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<UserDTO> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public List<UserGroupDTO> getAssignedGroups() {
        return assignedGroups;
    }

    public void setAssignedGroups(List<UserGroupDTO> assignedGroups) {
        this.assignedGroups = assignedGroups;
    }

}
