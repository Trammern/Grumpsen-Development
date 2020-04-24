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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
       projectUsers = selectedProject.getUsers();
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
    
}
