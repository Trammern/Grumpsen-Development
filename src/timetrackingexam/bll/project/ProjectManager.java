/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.project;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.dal.facade.IProjectDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;
import timetrackingexam.dal.mockdata.MockProjectManager;

/**
 *
 * @author math2
 */
public class ProjectManager implements IProjectManager {
    private final IProjectDal projectDal;

    public ProjectManager() {
        projectDal = new TimeTrackDalFacade();
    }
    // Gets all the projects from a List
    @Override
    public List<Project> getAllProjects(){
        return projectDal.getProjects();
    }
    // Creates a New Project
    @Override
    public boolean createNewProject(Project p){
        return projectDal.createNewProject(p);
    }
    // Updates a project
    @Override
    public boolean updateProject(Project p) {
        return projectDal.updateProject(p);
    }
    // Deletes the Project
    @Override
    public boolean deleteProject(Project project) {
        return projectDal.deleteProject(project);
    }
    // Get the CSV
    public void getCSV()
    {
       projectDal.getCSV();
    }
}
