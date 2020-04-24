/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class AddEditProjectViewController implements Initializable {

    private AppModel am;
    
    @FXML
    private JFXTextField fldName;
    @FXML
    private JFXTextField fldClient;
    @FXML
    private JFXTextField fldRate;
    @FXML
    private JFXTextField fldDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
    }    

    @FXML
    private void CreateProject(ActionEvent event) {

        
        Project newProject = new Project(0, fldName.getText());
            if(am.createNewProject(newProject)){
                System.out.println("project added");
                Stage primStage = (Stage) fldName.getScene().getWindow();
                primStage.close();
            }
        
        
        
    }
    
}
