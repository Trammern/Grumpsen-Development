/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.bll.client.IClientManager;
import timetrackingexam.bll.project.IProjectManager;
import timetrackingexam.bll.task.ITaskManager;
import timetrackingexam.bll.user.IUserManager;

/**
 *
 * @author Rizvan
 */
public interface ITimeTrackBLL extends ITaskManager, IProjectManager, IUserManager, IClientManager
{
  
}
