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
import timetrackingexam.dal.mockdata.MockProjectManager;

/**
 *
 * @author Rizvan
 */
public class TaskManager implements ITaskManager
{
    private Task task;
    private MockProjectManager mockProjectManager;

    public TaskManager()
    {
        mockProjectManager = new MockProjectManager();
    }

    
    
    @Override
    public boolean deleteTask(Task selectedTask, Project currentProject)
    {
        return mockProjectManager.deleteTask(selectedTask, currentProject);
    }


    @Override
    public List<Task> readTask()
    {
        return mockProjectManager.getTasks();
    }

    @Override
    public boolean createTask(Task t, Project p)
    {
        return mockProjectManager.createTask(t,p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return mockProjectManager.updateTask(updateTask);
    }

    public ObservableList<Task> getTimeUsed(Task t) {
        return mockProjectManager.getTimeUsed(t);
    }
    
    
    
}
