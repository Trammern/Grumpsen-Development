/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectManagementViewController implements Initializable {

    private final String ADD_EDIT_PATH = "/timetrackingexam/gui/view/promts/AddEditProjectView.fxml";
    
    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    
    @FXML
    private JFXListView<Project> lstProjects;
    @FXML
    private Text txtSelectedProject;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemPassword;
    @FXML
    private Menu menuUser;
    @FXML
    private JFXTextArea fldDescription;
    @FXML
    private MenuItem menuItemTasks;
    @FXML
    private MenuItem menuItemUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       am = AppModel.getInstance();
       lstProjects.setItems(getAllProjects());
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());
       am.setCurrentProject(null);
       am.setCurrentTask(null);
       am.setSelectedUser(null);
       txtSelectedProject.setText("(Select Project)");
    }    

    @FXML
    private void newProject(ActionEvent event) {
        am.setCurrentProject(null);
        openAddEditWindow();
    }

    @FXML
    private void editProject(ActionEvent event) {
        if(am.getCurrentProject()!=null){
            openAddEditWindow();
        }
        else {
            AlertBox.errorAlert("Select a project to edit");
        }
    }

    @FXML
    private void setSelectedProject(MouseEvent event) {
        selectedProject = lstProjects.getSelectionModel().getSelectedItem();
        am.setCurrentProject(selectedProject);
        txtSelectedProject.setText(selectedProject.toString());
        fldDescription.setText(selectedProject.getDescription());
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

    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }

    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.logout(primStage);
    }

    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }

    @FXML
    private void goToTasks(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectsOverview(primStage);
    }

    @FXML
    private void goToUserManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.userManagementView(primStage);
    }

    
    
}
