/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author math2
 */
public class Project {
    private String projectName;
    private int id;
    private int clientId;
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public Project(String projectName, int id, int clientId, ObservableList<Task> tasks) {
        this.projectName = projectName;
        this.id = id;
        this.clientId = clientId;
        this.tasks = tasks;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
    
    private boolean addTaskToProject(Task task){
        return tasks.add(task);
    }

    @Override
    public String toString() {
        return projectName;
    }

    
    
    
}
