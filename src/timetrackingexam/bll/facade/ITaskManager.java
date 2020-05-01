/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.facade;

import java.util.List;
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
    
    public boolean updateTask(Task updateTask, Project p);
    
    public List<Task> readTask();
     
}
