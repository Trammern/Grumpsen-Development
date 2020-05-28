/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

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

    public TaskLog(Date date, String action)
    {
        this.date = date;
        this.action = action;
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
    
    
    
    
}
