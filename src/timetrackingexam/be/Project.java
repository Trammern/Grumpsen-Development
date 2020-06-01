/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fauxtistic
 */
public class Project {
    
    private final static int DEFAULT_RATE = 150;
    
    private int id;
    private String name;
    private String description;
    private int rate;
    private int clientID;
    private ObservableList<Task> tasks;
    private ObservableList<User> users;
    
    public Project(String name, int clientID, String description, int rate) {
        this.name = name;
        this.clientID = clientID;
        this.description = description;
        this.rate = rate;
    }

    public Project(String name, String description, int clientID) {
        this.name = name;
        this.description = description;
        this.clientID = clientID;
        this.rate = DEFAULT_RATE;
        users = FXCollections.observableArrayList();
        tasks = FXCollections.observableArrayList();
    }

    public Project(int id, String name, String description, int rate, int clientID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.clientID = clientID;
    }           
    // Getter
    public int getId() {
        return id;
    }
    // Setter 
    public void setId(int id) {
        this.id = id;
    }
    // Getter
    public String getName() {
        return name;
    }
    // Setter 
    public void setName(String name) {
        this.name = name;
    }
    // Getter
    public int getRate() {
        return rate;
    }
    // Setter 
    public void setRate(int rate) {
        this.rate = rate;
    }
    // Getter
    public int getClientID() {
        return clientID;
    }
    // Setter 
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    // Getter
    public ObservableList<Task> getTasks() {
        return tasks;
    }
    // Setter 
    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }
    // Getter
    public int getTimeUsedByUser(User user){
        int totalTimeUsed = 0;
        for (Task task : tasks) {
            if(user.getId() == task.getUserId()){
                totalTimeUsed = totalTimeUsed + task.getHoursUsed();
            }
        }
        return totalTimeUsed;
    }
    // Getter
    public ObservableList<User> getUsers() {
        return users;
    }
    // Setter 
    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }
    // Adds task
    public boolean addTask(Task t){
        return tasks.add(t);
    }
    // removes task
    public boolean removeTask(Task selectedTask)
    {
        return tasks.remove(selectedTask);
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
        final Project other = (Project) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    // creates the name of the task
    @Override
    public String toString() {
        return name;
    }
    // gets the description
    public String getDescription() {
        return description;
    }
    // Sets the Description
    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
    
    
    
    
}
