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
    
   
    /*
    vi bør nok overveje en anden måde at holde styr på hvilke klienter har
    hvilke projekter, vi kan alligevel ikke gemme objekter i en database
    lad os lave en metode at tilføje projekter i DAL laget i stedet hvis ikke
    Dette er den eneste måde.
    
    -mvh.
    Birins
    */
    
    public Project create(int id, String name, String description, int clientId, int rate) {
        Project project = new Project(name, clientId, description, rate);
        //client.getProjects().add(project);
        return project;
    }
    
}
