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
    
    private int id;
    private String name;
    private String description;
    private Project project;
    private User user;
    private List<TaskTime> timeUsed = new ArrayList<>();;
    private int quartersUsed;
    private int timeIndex = 0;
    private long timeGrowth = 0;

    protected Task(int id, String name, Project project, User user) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.user = user; 
    }
    /**
     * Temporary Constructor for testing purposes
     * @param id
     * @param name 
     */
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public int ConvertToQuarters(int min, int hours){
        int totalQuarters;
        totalQuarters = min/15 + hours * 4;
        
        return totalQuarters;
    }

    public boolean addTaskTime(TaskTime tt) {
        
                System.out.println("date added");
                return timeUsed.add(tt);
    }

    public List<TaskTime> getTimeUsed() {
        return timeUsed;
    }
    

    public int getId()
    {
        return id;
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
    
    public long timeGrowth(){
        timeGrowth = timeGrowth + timeUsed.get(timeIndex).getHours();
        timeIndex++;
        return timeGrowth;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    
    
    
    
}
