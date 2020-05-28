/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.time.LocalDateTime;
import java.util.Date;
import timetrackingexam.gui.model.AppModel;

/**
 *
 * @author Rizvan
 */
public class TaskLog
{
    private int id;
    private Date date;
    private String action;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double submittedTime;

    /**
     * For logging CRUD
     * @param date
     * @param action 
     */
    public TaskLog(Date date, String action)
    {
        this.date = date;
        this.action = action;
    }

    /**
     * For logging Time
     * @param startDate
     * @param endDate 
     * @param submittedTime 
     */
    public TaskLog(LocalDateTime startDate, LocalDateTime endDate, double submittedTime)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.submittedTime = submittedTime;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public double getSubmittedTime()
    {
        return submittedTime;
    }

    public void setSubmittedTime(double submittedTime)
    {
        this.submittedTime = submittedTime;
    }

    public LocalDateTime getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate)
    {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate)
    {
        this.endDate = endDate;
    }

    
    
    @Override
    public String toString()
    {
        return "TaskLog{" + "startDate=" + startDate + ", endDate=" + endDate + ", submittedTime=" + submittedTime + '}';
    }
    
    

    
    
    
    
    
    
}
