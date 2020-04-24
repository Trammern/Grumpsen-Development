/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectManagementViewController implements Initializable {

    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    
    @FXML
    private JFXListView<Project> lstProjects;
    @FXML
    private Text txtSelectedProject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       am = AppModel.getInstance();
       lstProjects.setItems(getAllProjects());
       currentUser = am.getCurrentUser();
       
    }    

    @FXML
    private void newProject(ActionEvent event) {
        
        am.createNewProject(null);
    }

    @FXML
    private void editProject(ActionEvent event) {
    }

    @FXML
    private void openProject(ActionEvent event) {
    }
    
    @FXML
    private void setSelectedProject(MouseEvent event) {
        selectedProject = lstProjects.getSelectionModel().getSelectedItem();
        am.setCurrentProject(selectedProject);
        txtSelectedProject.setText(selectedProject.toString());
    }

    private ObservableList<Project> getAllProjects(){
        return am.getProjects();
    }

    

    

    
    
}
