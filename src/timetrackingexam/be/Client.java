/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author fauxtistic
 */
public class Client {
    
    private int id;
    private String name;
    private int defaultrate; //use BigDecimal instead? different opinions
    private ObservableList<Project> projects;
       
    public Client(int id, String name, int defaultrate) {     
        this.id = id;
        this.name = name;
        this.defaultrate = defaultrate;
        projects = FXCollections.observableArrayList();
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultrate() {
        return defaultrate;
    }

    public void setDefaultrate(int defaultrate) {
        this.defaultrate = defaultrate;
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ObservableList<Project> projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
}
