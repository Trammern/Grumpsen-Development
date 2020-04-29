/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.ViewGuide;

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
    @FXML
    private Text txtCurrentTask;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        currentUser = am.getCurrentUser();
        txtCurrentProject.setText(am.getCurrentProject().getName());
        txtCurrentTask.setText(txtCurrentTask.getText() + " " + am.getCurrentTask());
    }    

    @FXML
    private void back(ActionEvent event) {
        try
            {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/ProjectOverview.fxml"));
                Parent root1 = (Parent) fxml.load();
                Stage primStage = (Stage) txtCurrentProject.getScene().getWindow();
                Stage stage = new Stage();
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
    private void goToTime(ActionEvent event) {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/TimerView.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initOwner((Stage) event.getSource());
            stage.showAndWait();
            stage.setTitle("Timer");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
