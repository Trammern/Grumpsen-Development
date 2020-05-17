/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.task;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

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
    
     
}
