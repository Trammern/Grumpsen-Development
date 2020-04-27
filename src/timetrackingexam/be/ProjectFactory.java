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
    
    public Project create(int id, String name, Client client, double rate) {
        Project project = new Project(id, name, client, rate);
        client.getProjects().add(project);
        return project;
    }
    
}
