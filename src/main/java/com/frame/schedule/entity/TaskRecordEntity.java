package com.frame.schedule.entity;

import com.frame.core.components.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@Table(name="sc_task_record",
        uniqueConstraints = @UniqueConstraint(columnNames = "taskExecuteCode"))
@Entity
public class TaskRecordEntity extends BaseEntity implements Schedulable {
    public enum Status{
        SCHEDULED,EXECUTEING,SUCCESS,WAIT_RETRY,FAILED;
    }
    
    @NotNull
    private String taskExecuteCode;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name="null"))
    private TaskEntity task;
    
    private Date planStartDate;
    
    private Date startDateTime;
    
    private Date finishDateTime;
    
    private String status;
    
    @Override
    @Transient
    public String getGroup() {
        return "DYNAMIC";
    }
    
    @Transient
    @Override
    public String getKey() {
        return id.toString();
    }
    
    @Override
    @Transient
    public String getCronExpression() {
        return new SimpleDateFormat("ss mm HH dd MM ? yyyy").format(planStartDate);
    }
    
    @Override
    @Transient
    public boolean isPaused() {
        return false;
    }
    
    public String getTaskExecuteCode() {
        return taskExecuteCode;
    }
    
    public TaskRecordEntity setTaskExecuteCode(String taskExecuteCode) {
        this.taskExecuteCode = taskExecuteCode;
        return this;
    }
    
    public TaskEntity getTask() {
        return task;
    }
    
    public TaskRecordEntity setTask(TaskEntity task) {
        this.task = task;
        return this;
    }
    
    public Date getPlanStartDate() {
        return planStartDate;
    }
    
    public TaskRecordEntity setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
        return this;
    }
    
    public Date getStartDateTime() {
        return startDateTime;
    }
    
    public TaskRecordEntity setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }
    
    public Date getFinishDateTime() {
        return finishDateTime;
    }
    
    public TaskRecordEntity setFinishDateTime(Date finishDateTime) {
        this.finishDateTime = finishDateTime;
        return this;
    }
    
    public String getStatus() {
        return status;
    }
    
    public TaskRecordEntity setStatus(String status) {
        this.status = status;
        return this;
    }
}
