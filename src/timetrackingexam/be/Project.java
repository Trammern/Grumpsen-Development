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
    private double rate;
    private int clientId;
    private ObservableList<Task> tasks;
    private ObservableList<User> users;
    
    public Project(int id, String name, int clientId) {
        this(id, name, clientId, DEFAULT_RATE);
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
        users = FXCollections.observableArrayList();
        tasks = FXCollections.observableArrayList();
    }
    
    

    protected Project(int id, String name, int clientId, double rate) {
        this.id = id;
        this.name = name;
        this.clientId = clientId;
        this.rate = rate;
        users = FXCollections.observableArrayList();
        tasks = FXCollections.observableArrayList();        
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public int getTimeUsedByUser(User user){
        int totalTimeUsed = 0;
        for (Task task : tasks) {
            if(user.getId() == task.getUserId()){
                totalTimeUsed = totalTimeUsed + task.getHoursUsed();
            }
        }
        return totalTimeUsed;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<User> users) {
        this.users = users;
    }
    
    public boolean addTask(Task t){
        return tasks.add(t);
    }
    
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

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
    
    
    
    
}
