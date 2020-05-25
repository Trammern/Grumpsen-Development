/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.time.LocalDate;

/**
 *
 * @author math2
 */
public class TaskTime {
    private int ID;
    private int timeID;
    private int taskid;
    private int userId;
    private int sec;
    private int min;
    private int hours;
    private LocalDate date;

    public TaskTime(int sec, int min, int hours) {
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        date = LocalDate.now();
    }
    /*
     * For realz
     */
    public TaskTime(int taskId, int userId, int sec, int min, int hours, LocalDate dateOfWeek) {
        this.taskid = taskId;
        this.userId = userId;
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        this.date = dateOfWeek;
    }
    
    
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimeID() {
        return timeID;
    }
    
    public void setTimeID(int timeID){
        this.timeID = timeID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
   
    
}
