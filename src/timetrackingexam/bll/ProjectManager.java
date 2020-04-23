/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.dal.mockdata.MockProjectManager;

/**
 *
 * @author math2
 */
public class ProjectManager {
    private MockProjectManager mpm;

    public ProjectManager() {
        mpm = new MockProjectManager();
    }
    
    public ObservableList<Project> getAllProjects(){
        return mpm.getProjects();
    }
    
    public ObservableList<Task> getAllTasks(){
        return mpm.getAllTasks();
    }
    
}
