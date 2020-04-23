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
public class Client {
    
    private String name;
    private double defaultrate; //use BigDecimal instead? different opinions
    private List<Project> projects;
       
    public Client(String name) {
        this.name = name;
    }
    
    public Client(String name, double defaultrate) {
        this.name = name;
        this.defaultrate = defaultrate;
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
    
    
    
}
