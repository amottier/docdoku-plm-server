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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value="ActivityDTO", description="This class is the representation of an {@link com.docdoku.plm.server.core.workflow.Activity} entity")
public class ActivityDTO implements Serializable {

    @ApiModelProperty(value = "Activity step")
    private int step;

    @ApiModelProperty(value = "Activity relaunch step")
    private Integer relaunchStep;

    @ApiModelProperty(value = "List of the tasks")
    private List<TaskDTO> tasks;

    @ApiModelProperty(value = "Final lifecycle state")
    private String lifeCycleState;

    @ApiModelProperty(value = "Workflow type")
    private ActivityType type;

    @ApiModelProperty(value = "Tasks to complete")
    private Integer tasksToComplete;

    @ApiModelProperty(value = "Complete flag")
    private boolean complete;

    @ApiModelProperty(value = "Stopped flag")
    private boolean stopped;

    @ApiModelProperty(value = "In progress flag")
    private boolean inProgress;

    @ApiModelProperty(value = "Todo flag")
    private boolean toDo;

    public ActivityDTO() {
        tasks = new ArrayList<>();
    }

    public ActivityDTO(int step, List<TaskDTO> tasks, String lifeCycleState, ActivityType type, Integer tasksToComplete, boolean complete, boolean stopped, boolean inProgress, boolean toDo, Integer relaunchStep) {
        this.step = step;
        this.relaunchStep = relaunchStep;
        this.tasks = tasks;
        this.lifeCycleState = lifeCycleState;
        this.type = type;
        this.tasksToComplete = tasksToComplete;
        this.complete = complete;
        this.stopped = stopped;
        this.inProgress = inProgress;
        this.toDo = toDo;
    }

    public Integer getTasksToComplete() {
        return tasksToComplete;
    }

    public void setTasksToComplete(Integer tasksToComplete) {
        this.tasksToComplete = tasksToComplete;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isToDo() {
        return toDo;
    }

    public void setToDo(boolean toDo) {
        this.toDo = toDo;
    }

    public String getLifeCycleState() {
        return lifeCycleState;
    }

    public void setLifeCycleState(String lifeCycleState) {
        this.lifeCycleState = lifeCycleState;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getRelaunchStep() {
        return relaunchStep;
    }

    public void setRelaunchStep(Integer relaunchStep) {
        this.relaunchStep = relaunchStep;
    }

}
