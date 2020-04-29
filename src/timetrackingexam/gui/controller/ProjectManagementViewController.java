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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;
import timetrackingexam.gui.util.ICommonMenuActions;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectManagementViewController implements Initializable, ICommonMenuActions {

    private final String ADD_EDIT_PATH = "/timetrackingexam/gui/view/AddEditProjectView.fxml";
    
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
        am.setCurrentProject(null);
        openAddEditWindow();
    }

    @FXML
    private void editProject(ActionEvent event) {
        if(am.getCurrentProject()!=null){
            openAddEditWindow();
        }
    }

    @FXML
    private void openProject(ActionEvent event) {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/AdminProjectOverview.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            Stage primStage = (Stage) txtSelectedProject.getScene().getWindow();
            stage.setScene(new Scene(root1));
            primStage.close();
            stage.showAndWait();
            stage.setTitle("Timer");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }

    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        logout(primStage);
    }

    
    
}
