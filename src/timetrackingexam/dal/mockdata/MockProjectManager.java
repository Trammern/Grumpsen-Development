/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.mockdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

/**
 *
 * @author math2
 */
public class MockProjectManager {

    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    
    public MockProjectManager() {
        initialize();
    }
    
    private void initialize(){
        Project p1 = new Project(1, "Project X");
        Project p2 = new Project(2, "Project Y");
        
        projects.add(p1);
        projects.add(p2);
        
        Task t1 = new Task(1, "Task1");
        Task t2 = new Task(2, "Task2");
        Task t3 = new Task(3, "Task3");
        Task t4 = new Task(4, "Task4");
        Task t5 = new Task(5, "Task5");
        Task t6 = new Task(6, "Task6");
        
        p1.addTask(t1);
        p1.addTask(t2);
        p1.addTask(t3);
        p2.addTask(t4);
        p2.addTask(t5);
        p2.addTask(t6);
        
        
        
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }
    
    public boolean createNewProject(Project p){
        return projects.add(p);
    }
    
}
