/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
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
    
    @FXML
    private TableView<User> tblEmployeeTable;
    @FXML
    private Text txtCurrentProject;
    @FXML
    private Text txtSalary;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
    }    

    @FXML
    private void addEmployee(ActionEvent event) {
    }

    @FXML
    private void removeEmployee(ActionEvent event) {
    }

    @FXML
    private void openTaskView(ActionEvent event) {
    }
    
}
