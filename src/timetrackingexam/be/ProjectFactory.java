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
public class ProjectFactory {
    
    public Project create(int id, String name, String description, int clientId, int rate) {
        Project project = new Project(name, clientId, description, rate);
        //client.getProjects().add(project);
        return project;
    }
    
}
