/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertFactory;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;
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
    @FXML
    private JFXButton btnNewProject;
    @FXML
    private JFXButton btnEditProject;
    @FXML
    private JFXComboBox<Client> cbbClients;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       am = AppModel.getInstance();
       cbbClients.setItems(am.getAllClients()); 
       
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());
       am.setCurrentProject(null);
       am.setCurrentTask(null);
       am.setSelectedUser(null);
       txtSelectedProject.setText("(Select Project)");
       
       initListener();
       initTooltips();
       initEffects();
    }    

    private void initListener() {
        cbbClients.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
            lstProjects.getItems().clear();
            showClientProjects(newValue);            
            });
    }
    
    private void initTooltips() {
        btnNewProject.setTooltip(TooltipFactory.create("Click here to create a new project"));        
        btnEditProject.setTooltip(TooltipFactory.create("Click here to edit an existing project.\nSelect a project first"));
    }
    
    private void initEffects() {
        NodeCustomizer.nodeEffect(btnNewProject);
        NodeCustomizer.nodeEffect(btnEditProject);
    }
    
    private void showClientProjects(Client client) {
        lstProjects.setItems(am.getAllClientProjects(client));
        
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
            AlertFactory.showErrorAlert("Select a project to edit");
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
            AlertFactory.showErrorAlert("Could not open new window");
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
