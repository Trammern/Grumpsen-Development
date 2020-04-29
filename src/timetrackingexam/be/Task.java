/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

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
    private TaskTime timeUsed;
    private int quartersUsed;

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

    public TaskTime getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(TaskTime timeUsed) {
        this.timeUsed = timeUsed;
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
