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
    
    private int id;
    private String name;
    private String description;
    private int rate;
    private int clientID;
    
    public Project(String name, int clientID, String description, int rate) {
        this.name = name;
        this.clientID = clientID;
        this.description = description;
        this.rate = rate;
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
    // gets the description
    public String getDescription() {
        return description;
    }
    // Sets the Description
    public void setDescription(String description) {
        this.description = description;
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
    

    
    
    
    
    
    
    
}
