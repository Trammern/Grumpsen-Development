/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import java.util.List;
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
    public boolean deleteTask()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTask()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Task> readTask()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTask(Task t, Project p)
    {
        return mockProjectManager.createTask(t,p);
    }
    
    
    
}
