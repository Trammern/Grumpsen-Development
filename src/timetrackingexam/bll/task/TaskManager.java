/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.task;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.utilities.StatisticsCalculator;
import timetrackingexam.dal.facade.IProjectDal;
import timetrackingexam.dal.mockdata.MockProjectManager;
/**
 *
 * @author Rizvan
 */
public class TaskManager implements ITaskManager
{
    private Task task;
    private StatisticsCalculator cal;
    private final ITaskManager taskManager;

    public TaskManager()
    {
        cal = new StatisticsCalculator();
        taskManager = new TimeTrackBLLFacade();
    }

    
    
    @Override
    public boolean deleteTask(Task selectedTask, Project currentProject)
    {
        return taskManager.deleteTask(selectedTask, currentProject);
    }


    @Override
    public ObservableList<Task> readTask()
    {
        return taskManager.readTask();
    }

    @Override
    public boolean createTask(Task t, Project p)
    {
        return taskManager.createTask(t,p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return taskManager.updateTask(updateTask);
    }

    public long getLineChartData(Task t) {
        return cal.timeGrowth(t);
    }

    @Override
    public TaskTime getTime(Task t) {
        return taskManager.getTime(t);
    }
    
    

    @Override
    public boolean updateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
