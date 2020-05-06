/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

/**
 *
 * @author fauxtistic
 */
public interface IProjectDal {
    
    public ObservableList<Project> getProjects();
    public ObservableList<Task> getTasks();
    public boolean createNewProject(Project p);
    public boolean createTask(Task t, Project p);
    public boolean updateProject(Project p);
    public boolean deleteTask(Task selectedTask, Project currentProject);
    public boolean updateTask(Task updateTask);
    public ObservableList<Task> getTimeUsed(Task t);
    
}
