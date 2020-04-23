/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.List;

/**
 *
 * @author fauxtistic
 */
public class Project {
    
    private int id;
    private String name;
    private double rate;
    private Client client;
    private List<Task> tasks;
    private List<User> users;
    
    public Project(int id, String name, Client client) {
        this(id, name, client, client.getDefaultrate());
    }

    public Project(int id, String name, Client client, double rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    
    
}
