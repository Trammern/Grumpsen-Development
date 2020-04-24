/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.facade;

import java.util.List;
import timetrackingexam.be.Task;

/**
 *
 * @author Rizvan
 */
public interface ITaskManager
{
    public Task createTask();
    public boolean deleteTask();
    public boolean updateTask();
    public List<Task> readTask();
     
}
