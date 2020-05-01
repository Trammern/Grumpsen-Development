/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());
       setUserTable();
       //DER ER ET ELLER ANDET GALT MED DENNE HER LINJE
       //projectUsers = selectedProject.getUsers();
       
       
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
        User user = tblEmployeeTable.getSelectionModel().getSelectedItem();
        selectedProject.getUsers().add(user);  
        //selectedProject.getUsers();
    }

    @FXML
    private void removeEmployee(ActionEvent event) {
        User user = tblEmployeeTable.getSelectionModel().getSelectedItem();
        selectedProject.getUsers().remove(user);  
        //selectedProject.getUsers();
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
    
    private void setUserTable(){
        tblEmployeeTable.setItems(am.GetProjectEmployees(am.getCurrentProject()));
    }
    
}
