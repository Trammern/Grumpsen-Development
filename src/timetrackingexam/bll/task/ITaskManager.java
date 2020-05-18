/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.task;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;

/**
 *
 * @author Rizvan
 */
public interface ITaskManager
{
    
    //void for mockdata
    public boolean createTask(Task t, Project p);
    
    public boolean deleteTask(Task selectedTask, Project currentProject);
    
    public boolean updateTask(Task updateTask);
    
    public ObservableList<Task> readTask();
    
    public TaskTime getTime(Task t);
    
    public boolean updateTime(TaskTime tt);
    
    public boolean addTime(TaskTime tt, Task t);
    
     
}
