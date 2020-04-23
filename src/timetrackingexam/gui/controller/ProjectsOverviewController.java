/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectsOverviewController implements Initializable {
    
    private AppModel am = new AppModel();
    private Project selectedProject;

    @FXML
    private JFXComboBox<Project> cbbProjectSelect;
    @FXML
    private JFXListView<Task> lstTaskList;
    @FXML
    private Text txtSlectedTask;
    @FXML
    private JFXTextArea txtTaskDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbbProjectSelect.setItems(getAllProjects());
        
    }    

    @FXML
    private void addTask(ActionEvent event) {
    }

    @FXML
    private void OpenTask(ActionEvent event) {
    }
    
    @FXML
    private void setItemsOnList(ActionEvent event) {
        selectedProject = cbbProjectSelect.getSelectionModel().getSelectedItem();
        lstTaskList.setItems(selectedProject.getTasks());
    }
    
    private ObservableList<Project> getAllProjects(){
       return am.getProjects();
    }

    
    
}
