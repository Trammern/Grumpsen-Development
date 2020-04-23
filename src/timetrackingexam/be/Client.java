/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fauxtistic
 */
public class Client {
    
    private String name;
    private double defaultrate; //use BigDecimal instead? different opinions
    private List<Project> projects;
       
    public Client(String name) {        
        this.name = name;
        projects = new ArrayList<>();
    }
    
    public Client(String name, double defaultrate) {
        this.name = name;        
        this.defaultrate = defaultrate;
        projects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDefaultrate() {
        return defaultrate;
    }

    public void setDefaultrate(double defaultrate) {
        this.defaultrate = defaultrate;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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
    
    
    
}
