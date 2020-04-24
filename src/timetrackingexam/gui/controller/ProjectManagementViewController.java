/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectManagementViewController implements Initializable {

    private final String ADD_EDIT_PATH = "/timetrackingexam/gui/view/AddEditProjectView.fxml";
    
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
        openAddEditWindow();
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

    private void openAddEditWindow(){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(ADD_EDIT_PATH));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            stage.setTitle("Create Project");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            AlertBox.errorAlert("Could not open new window");
        }
    }

    
    
}
