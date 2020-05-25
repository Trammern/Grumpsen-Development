/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.task;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.utilities.StatisticsCalculator;
import timetrackingexam.dal.facade.IProjectDal;
import timetrackingexam.dal.facade.ITaskDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;
import timetrackingexam.dal.mockdata.MockProjectManager;
/**
 *
 * @author Rizvan
 */
public class TaskManager implements ITaskManager
{
    private Task task;
    private StatisticsCalculator cal;
    private final ITaskDal taskManager;

    public TaskManager()
    {
        taskManager = new TimeTrackDalFacade();
    }

    
    
    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return taskManager.deleteTask(task);
    }


    @Override
    public ObservableList<Task> getTasks(Project p)
    {
        return taskManager.getTasks(p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return taskManager.updateTask(updateTask);
    }

    @Override
    public ObservableList<TaskTime> getTime(Task t) {
        return taskManager.getTime(t);
    }
    
    @Override
    public boolean updateTime(TaskTime tt) {
        return taskManager.updateTime(tt);
    }

    @Override
    public boolean submitTime(TaskTime tt, Task t) {
            return taskManager.submitTime(tt, t);
    }

    @Override
    public boolean addTask(Task t, Project p) {
        return taskManager.addTask(t, p);
    }

    @Override
    public TaskTime getTotalTime(Task currentTask) {
        System.out.println("I was called");
        int totalHour=0;
        int totalMin=0;
        int totalSec=0;
        
        for (TaskTime time : taskManager.getTime(currentTask)) {
            totalHour += time.getHours();
            totalMin += time.getMin();
            totalSec += time.getSec();
        }
        TaskTime totalTime = new TaskTime(totalSec, totalMin, totalHour);
        return totalTime;
    }

    
    
    
}
