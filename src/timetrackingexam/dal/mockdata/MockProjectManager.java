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
import timetrackingexam.be.TaskTime;

/**
 *
 * @author math2
 */
public class MockProjectManager {

    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    
    public MockProjectManager() {
        initialize();
    }
    
    private void initialize(){
        Project p1 = new Project(1, "Project X");
        Project p2 = new Project(2, "Project Y");
        Project p3 = new Project(3, "Project Z");
        
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);
        
        Task t1 = new Task(1, "Task1");
        Task t2 = new Task(2, "Task2");
        Task t3 = new Task(3, "Task3");
        Task t4 = new Task(4, "Task4");
        Task t5 = new Task(5, "Task5");
        Task t6 = new Task(6, "Task6");
        
        Task t7 = new Task("Create something", "Description of something");
        
        TaskTime tt1 = new TaskTime(45, 4, 1);
        TaskTime tt2 = new TaskTime(5, 55, 5);
        TaskTime tt3 = new TaskTime(14, 1, 12);
        t1.setTimeUsed(tt1);
        t2.setTimeUsed(tt2);
        t3.setTimeUsed(tt3);
        
        p1.addTask(t1);
        p1.addTask(t2);
        p1.addTask(t3);
        p2.addTask(t4);
        p2.addTask(t5);
        p2.addTask(t6);
        p3.addTask(t7);
        
        
        
        
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }
    
    public ObservableList<Task> getTasks()
    {
        return tasks;
    }
    
    public boolean createNewProject(Project p){
        return projects.add(p);
    }

    public boolean createTask(Task t, Project p)
    {
        return p.addTask(t);
    }

    public boolean updateProject(Project p) {
        for (Project project : projects) {
            if(p.getId() == project.getId()){
                project = p;
                return true;
            }
        }
        return false;
    }


    public boolean deleteTask(Task selectedTask, Project currentProject)
    {
        return currentProject.removeTask(selectedTask);
    }

    public boolean updateTask(Task updateTask)
    {
        throw new UnsupportedOperationException();
    }
}
