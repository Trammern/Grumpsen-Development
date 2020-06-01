/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fauxtistic
 */
public class Task {
    
    private int id;
    private int projectId;
    private int userId;
    private String name;
    private String description;
    private int timeAssigned;
    
    
    private List<TaskTime> timeUsed = new ArrayList<>();

    /**
     * Constructor for creating tasks
     * @param projectId
     * @param userId
     * @param name 
     * @param description 
     */
    // Creates the task to the project ID
    public Task(int projectId, int userId, String name, String description) {
        this.projectId = projectId;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor for editing Tasks 
     * @param name
     * @param description 
     */
    // Adds the name and the desciption to the task
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
    // Gets name
    public String getName() {
        return name;
    }
    //Sets Name
    public void setName(String name) {
        this.name = name;
    }
    //Gets UserId
    public int getUserId() {
        return userId;
    }
    // Sets UserID
    public void setUserId(int userId) {
        this.userId = userId;
    }
    //Gets ProjectId
    public int getProjectId() {
        return projectId;
    }
    //Sets ProjectId
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    //Gets Id
    public int getId() {
        return id;
    }
    //Sets Id
    public void setId(int id) {
        this.id = id;
    }
    // Gets Description
    public String getDescription()
    {
        return description;
    }
    // Sets Description
    public void setDescription(String description)
    {
        this.description = description;
    }
    // Gets TimeAssigned
    public int getTimeAssigned() {
        return timeAssigned;
    }
    // Sets TimeAssigned
    public void setTimeAssigned(int timeAssigned) {
        this.timeAssigned = timeAssigned;
    }
    // Gets HoursUsed
    public int getHoursUsed(){
        int hoursUsed = 0;
        for (TaskTime taskTime : timeUsed) {
            hoursUsed = hoursUsed + taskTime.getHours();
        }
        
        return hoursUsed;
    }

    
   
         
    
    //Gets timeUsed
    public List<TaskTime> getTimeUsed() {
        return timeUsed;
    }
    //Gets TotalTimeUsed
    public TaskTime getTotalTimeUsed() {
        int sec = 0;
        int min = 0;
        int hour = 0;
        
        for (TaskTime taskTime : timeUsed) {
            sec = sec + taskTime.getSec();
            min = min + taskTime.getMin();
            hour = hour + taskTime.getHours();
        }
        
        return new TaskTime(sec, min, hour);
    }
    
    
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (this.userId != other.userId && this.projectId != other.projectId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    
    
    
    
}
