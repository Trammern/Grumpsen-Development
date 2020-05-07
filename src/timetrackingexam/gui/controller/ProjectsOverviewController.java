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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectsOverviewController implements Initializable {
    
    private AppModel am;
    private User currentUser;
    private Project selectedProject;

    @FXML
    private JFXComboBox<Project> cbbProjectSelect;
    @FXML
    private JFXListView<Task> lstTaskList;
    @FXML
    private Label txtSlectedTask;
    @FXML
    private JFXTextArea txtTaskDescription;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXButton btnAddTask;
    @FXML
    private JFXButton btnEditTask;
    @FXML
    private JFXButton btnDeleteTask;
    @FXML
    private Menu menuUser;
    @FXML
    private MenuItem menuItemPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        currentUser = am.getCurrentUser();
        menuUser.setText(currentUser.getEmail());
        cbbProjectSelect.setItems(am.getProjects());
        selectedProject = am.getProjects().get(0);
        am.setCurrentProject(selectedProject);
        lstTaskList.setItems(selectedProject.getTasks());
        txtSlectedTask.setText("(Select Project)");
    } 

    @FXML
    private void addTask(ActionEvent event) {
        am.setCurrentTask(null);
        openAddEdit();
    }

    @FXML
    private void OpenTask(ActionEvent event) {
        if(am.getCurrentTask() != null){
            try
            {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/TaskOverview.fxml"));
                Parent root1 = (Parent) fxml.load();
                Stage primStage = (Stage) txtSlectedTask.getScene().getWindow();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                primStage.close();
                stage.showAndWait();
                stage.setTitle("Task Overview");
            
            
            } catch (IOException ex)
            {   
                Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void setItemsOnList(ActionEvent event) {
        selectedProject = cbbProjectSelect.getSelectionModel().getSelectedItem();        
        lstTaskList.setItems(selectedProject.getTasks());
        am.setCurrentProject(selectedProject);      
    }

    @FXML
    private void setCurrentTask(MouseEvent event) {
        if (lstTaskList.getSelectionModel().getSelectedItem() != null) {
            am.setCurrentTask(lstTaskList.getSelectionModel().getSelectedItem());
            txtSlectedTask.setText(am.getCurrentTask().getName());
        }
        
        if (am.getCurrentTask().getDescription() == null)
        {
            txtTaskDescription.setStyle("-fx-font-style: italic;");
            txtTaskDescription.setText("No available description found for this task");
        }
        else
        {
            txtTaskDescription.setStyle("-fx-font-style: normal;");
            txtTaskDescription.setText(am.getCurrentTask().getDescription());
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
    
    private void openAddEdit()
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/AddTask.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("New Task");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleEditTask(ActionEvent event)
    {
        if (am.getCurrentTask() != null)
        {
            openAddEdit();
        }
    }

    @FXML
    private void handleDeleteTask(ActionEvent event)
    {
        Task selectedTask = lstTaskList.getSelectionModel().getSelectedItem();
        Project currentProject = am.getCurrentProject();
        if (selectedTask != null)
        {
            am.removeTask(selectedTask, currentProject);
        }
        else
        {
            System.out.println("Please choose the task you want to delete");
        }
    }

    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }

    @FXML
    private void handleBarChart(ActionEvent event) {
        
        try
            {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/AssigenedTime.fxml"));
                Parent root1 = (Parent) fxml.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.showAndWait();
                stage.setTitle("Assigned Time For Tasks");
            
            } catch (IOException ex)
            {   
                Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void handlePieChart(ActionEvent event)
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/UsedTimePerTask.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initOwner((Stage) menuBar.getScene().getWindow());
            stage.showAndWait();
            stage.setTitle("Time used");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    }
    
    
    

