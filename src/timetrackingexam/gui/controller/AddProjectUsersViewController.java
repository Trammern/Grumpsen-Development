/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class AddProjectUsersViewController implements Initializable {

    private AppModel am;
    private Project selectedProject;    
    
    @FXML
    private JFXButton btnAddUsers;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private TableView<User> tblUsers;
    @FXML
    private TableColumn<User, String> tblColumnUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        selectedProject = am.getCurrentProject();
        tblUsers.setItems(am.getProjectNonEmployees(selectedProject));
        tblUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        initColumns();        
    }    
    
    private void initColumns() {
        tblColumnUser.setCellValueFactory(data -> {
            String name = data.getValue().getLastName() + ", " + data.getValue().getFirstName();
            return new SimpleStringProperty(name);
        });
    }

    @FXML
    private void addUserToProject(ActionEvent event) {
        List<User> users = tblUsers.getSelectionModel().getSelectedItems();
        if (users.isEmpty()) {
            AlertBox.errorAlert("You must select one or more users to add to the project");            
        }
        else {
            am.addUsersToProject(selectedProject, users);
            Stage stage = (Stage) btnAddUsers.getScene().getWindow();
            stage.close();
        }
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
}
