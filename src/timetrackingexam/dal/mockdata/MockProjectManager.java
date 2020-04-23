/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.mockdata;

import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

/**
 *
 * @author math2
 */
public class MockProjectManager {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<Project> projects = FXCollections.observableArrayList();
    
    public MockProjectManager() {
        initialize();
        projects.add(new Project("Project X", 0, 0, tasks));
    }
    
    private void initialize(){
        Task task1 = new Task(LocalTime.now(), "task1", 1, 1);
        Task task2 = new Task(LocalTime.now(), "task2", 1, 2);
        Task task3 = new Task(LocalTime.now(), "task3", 1, 3);
        Task task4 = new Task(LocalTime.now(), "task4", 1, 4);
        
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }
    
    public ObservableList<Task> getAllTasks(){
        return tasks;
    }
}
