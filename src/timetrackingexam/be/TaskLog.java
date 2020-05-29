
package timetrackingexam.be;

import java.time.LocalDateTime;
import java.util.Date;

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
    private String submittedTimeStringFormat;
    private User createdBy;
    private String taskName;

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
        submittedTimeStringFormat = submittedTime + " sek";
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
    
    public String getSubmittedTimeStringFormat()
    {
        return submittedTimeStringFormat;
    }

    public void setSubmittedTime(double submittedTime)
    {
        submittedTimeStringFormat = submittedTime + " sek";
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

    public User getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    
    

    
    
    
    
    
    
}
