/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.bll.facade.ITaskManager;
import timetrackingexam.dal.facade.IProjectDal;
import timetrackingexam.dal.mockdata.MockProjectManager;

/**
 *
 * @author Rizvan
 */
public class TaskManager implements ITaskManager
{
    private Task task;
    private IProjectDal projectDal;

    public TaskManager()
    {
        projectDal = new MockProjectManager();
    }

    
    
    @Override
    public boolean deleteTask(Task selectedTask, Project currentProject)
    {
        return projectDal.deleteTask(selectedTask, currentProject);
    }


    @Override
    public List<Task> readTask()
    {
        return projectDal.getTasks();
    }

    @Override
    public boolean createTask(Task t, Project p)
    {
        return projectDal.createTask(t,p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return projectDal.updateTask(updateTask);
    }

    public ObservableList<Task> getTimeUsed(Task t) {
        return projectDal.getTimeUsed(t);
    }
    
    
    
}
