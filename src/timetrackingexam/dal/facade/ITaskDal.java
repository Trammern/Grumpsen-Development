/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;

/**
 *
 * @author math2
 */
public interface ITaskDal {
    public Task getSpecificTask(Task task);
    public ObservableList<Task> getAllTasks();
    public boolean createTask(Task task);
    public boolean deleteTask(Task task);
    public boolean updateTask(Task task);
    
    public ObservableList<TaskTime> getTaskTime(Task task);
    
    
}
