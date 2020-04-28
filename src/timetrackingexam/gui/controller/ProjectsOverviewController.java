/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        currentUser = am.getCurrentUser();
        cbbProjectSelect.setItems(am.getProjects());
        selectedProject = am.getProjects().get(0);
        am.setCurrentProject(selectedProject);
        lstTaskList.setItems(selectedProject.getTasks());
    } 

    @FXML
    private void addTask(ActionEvent event) {
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
            am.setCurrentProject(lstTaskList.getSelectionModel().getSelectedItem());
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
    
    
    
}
