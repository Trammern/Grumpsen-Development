/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fauxtistic
 */
public class Task {
    
    private int projectId;
    private int userId;
    private String name;
    private String description;
    private int timeAssigned;
    
    private List<TaskTime> timeUsed = new ArrayList<>();

    public Task(int projectId, int userId, String name, int timeAssigned) {
        this.projectId = projectId;
        this.userId = userId; 
        this.name = name;
        this.timeAssigned = timeAssigned;
    }
    /**
     * Temporary Constructor for testing purposes
     * @param id
     * @param name 
     * @param timeAssigned
     */
    public Task(int id, String name, int timeAssigned) {
        this.name = name;
        this.timeAssigned = timeAssigned;
    }

    public Task(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getTimeAssigned() {
        return timeAssigned;
    }

    public void setTimeAssigned(int timeAssigned) {
        this.timeAssigned = timeAssigned;
    }
    
    public int getHoursUsed(){
        int hoursUsed = 0;
        for (TaskTime taskTime : timeUsed) {
            hoursUsed = hoursUsed + taskTime.getHours();
        }
        
        return hoursUsed;
    }
    
    

    public boolean addTaskTime(TaskTime tt) {
        /*for (TaskTime taskTime : timeUsed) {
            if(tt.getId() == taskTime.getId()){
                taskTime.setHours(tt.getHours());
                taskTime.setHours(tt.getMin());
                taskTime.setHours(tt.getSec());
                System.out.println("Date Updated");
                return true;
            }
        }*/
        System.out.println("date added");
        return timeUsed.add(tt);
         
    }

    public List<TaskTime> getTimeUsed() {
        return timeUsed;
    }
    
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
