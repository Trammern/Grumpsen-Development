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
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class AdminProjectOverviewController implements Initializable {

    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    private ObservableList<User> projectUsers;
    private static final String ADD_EDIT_USER_VIEW_PATH = "/timetrackingexam/gui/view/AddEditUserView.fxml";    
    private static final String ADD_PROJECT_USERS_VIEW_PATH = "/timetrackingexam/gui/view/AddProjectUsersView.fxml";    
    
    @FXML
    private TableView<User> tblEmployeeTable;
    @FXML
    private Text txtCurrentProject;
    @FXML
    private Text txtSalary;
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
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());             
       setProject(am.getCurrentProject());
       projectUsers = am.GetProjectEmployees(selectedProject);
       tblEmployeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); 
       tblEmployeeTable.setItems(projectUsers);       
              
       initColumns();
    }    
    
    private void initColumns() {
        columnName.setCellValueFactory(data -> {
            String name = data.getValue().getLastName() + ", " + data.getValue().getFirstName();
            return new SimpleStringProperty(name);
        });
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        Stage primStage = (Stage) btnAdd.getScene().getWindow();
        ViewGuide.openView(ADD_PROJECT_USERS_VIEW_PATH, "Add users to project", primStage, false, true);
        am.fetch();
        
    }

    @FXML
    private void removeEmployee(ActionEvent event) {
        List<User> users = tblEmployeeTable.getSelectionModel().getSelectedItems();
        for (User user : users) {
            user.removeUser(selectedProject);
        }        
        am.fetch();
    }

    @FXML
    private void openTaskView(ActionEvent event) {
    }
    
    private void setProject(Project project) {
        selectedProject = project;
        txtCurrentProject.setText(selectedProject.getName());
        txtSalary.setText("Hourly Salary: " + selectedProject.getRate());
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
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem());
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
    
}
