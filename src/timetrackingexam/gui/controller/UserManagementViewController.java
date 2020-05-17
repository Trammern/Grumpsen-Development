/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;
import timetrackingexam.gui.util.TooltipFactory;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class UserManagementViewController implements Initializable {

    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    private ObservableList<User> allUsers;
    private static final String ADD_EDIT_USER_VIEW_PATH = "/timetrackingexam/gui/view/promts/AddEditUserView.fxml";
      
    private static final String PROJECT_MANAGEMENT_VIEW_PATH = "/timetrackingexam/gui/view/ProjectManagementView.fxml";    
    
    @FXML
    private TableView<User> tblEmployeeTable;   
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private Menu menuUser;
    @FXML
    private MenuItem menuItemPassword;
    @FXML
    private JFXButton btnNewUser;
    @FXML
    private JFXButton btnEditUser;    
    @FXML
    private TableColumn<User, String> columnBilHours;
    @FXML
    private TableColumn<User, String> columnTotalHours;
    @FXML
    private MenuItem menuItemProject;
    @FXML
    private MenuItem menuItemTask;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());           
       allUsers = am.getAllUsers();
       tblEmployeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
       tblEmployeeTable.setItems(allUsers);       
              
       initColumns();
       initTooltips();
    }    
    
    private void initColumns() {
        columnName.setCellValueFactory(data -> {
            String name = data.getValue().getLastName() + ", " + data.getValue().getFirstName();
            return new SimpleStringProperty(name);
        });
        
        columnTotalHours.setCellValueFactory(data -> {
            //String totalHours = am.getCurrentProject().getTimeUsedByUser(data.getValue())+"";
            String totalHours = "";
            return new SimpleStringProperty(totalHours);
        });
    }
    
    private void initTooltips() {
        btnNewUser.setTooltip(TooltipFactory.create("Click here to create a new employee profile", 500, 250));        
        btnEditUser.setTooltip(TooltipFactory.create("Click here to edit an existing employee profile.\nSelect a user first", 500, 250));
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
    private void newUser(ActionEvent event) {
        am.setSelectedUser(null); 
        Stage primStage = (Stage) btnEditUser.getScene().getWindow();
        ViewGuide.openView(ADD_EDIT_USER_VIEW_PATH, "New user", primStage, false, true);
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem()); //why?
    }

    @FXML
    private void editUser(ActionEvent event) {
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem());
        Stage primStage = (Stage) btnEditUser.getScene().getWindow();
        if (am.getSelectedUser()!=null) {
            ViewGuide.openView(ADD_EDIT_USER_VIEW_PATH, "Edit user", primStage, false, true);
        }
        else {
            AlertBox.errorAlert("Select a user to edit");
        }
    }       

    @FXML
    private void goToProjectManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectManagementView(primStage);
    }

    @FXML
    private void goToTasks(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectsOverview(primStage);
    }
    
    
}
