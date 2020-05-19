/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;

/**
 *
 * @author math2
 */
public interface ITaskDal {
    public Task getSpecificTask(Task task);
    public ObservableList<Task> getTasks(Project p);
    public boolean createTask(Task task);
    public boolean deleteTask(Task task);
    public boolean updateTask(Task task);
    public boolean deleteTask(Task selectedTask, Project currentProject);
    public boolean createTask(Task t, Project p);
    
    public TaskTime getTime(Task t);
    public boolean addTime(TaskTime tt, Task t);
    public boolean updateTime(TaskTime tt);

    
    
}
