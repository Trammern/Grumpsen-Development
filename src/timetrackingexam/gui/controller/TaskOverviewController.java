/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class TaskOverviewController implements Initializable {

    private AppModel am;
    private User currentUser;
    
    @FXML
    private JFXTextArea txtTaskDescription;
    @FXML
    private Text txtCurrentProject;
    @FXML
    private Text txtMinutesUsed;
    @FXML
    private Text txtStartDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        currentUser = am.getCurrentUser();
    }    

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void goToTime(ActionEvent event) {
    }
    
}
