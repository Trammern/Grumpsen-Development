/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectManagementViewController implements Initializable {

    @FXML
    private JFXListView<?> lstProjects;
    @FXML
    private Text txtSelectedProject;
    @FXML
    private TextArea txtProjectDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newProject(ActionEvent event) {
    }

    @FXML
    private void editProject(ActionEvent event) {
    }

    @FXML
    private void openProject(ActionEvent event) {
    }
    
}