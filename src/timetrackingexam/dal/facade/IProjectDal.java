/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

/**
 *
 * @author fauxtistic
 */
public interface IProjectDal {
    
    public ObservableList<Project> getProjects();
    public boolean createNewProject(Project p);
    public boolean addTask(Task t, Project p);
    public boolean updateProject(Project p);
    public boolean deleteProject(Project project);
    public void getCSV();

}
