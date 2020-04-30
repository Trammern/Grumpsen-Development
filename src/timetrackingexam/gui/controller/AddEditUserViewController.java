/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class AddEditUserViewController implements Initializable {

    private AppModel am;
    private User currentUser;
    private User selectedUser;
    
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private ComboBox<User.Role> cbbRole;
    @FXML
    private JFXButton btnSaveUser;
    @FXML
    private JFXButton btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        selectedUser = am.getSelectedUser();
        List<User.Role> roles = Arrays.asList(User.Role.values());
        cbbRole.setItems(FXCollections.observableArrayList(roles));   
        if (selectedUser != null) {
            setUserInformation(selectedUser);
        }
    }    

    private void setUserInformation(User user) {
        txtEmail.setText(user.getEmail());
        txtPassword.setText(user.getPassword());
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        cbbRole.getSelectionModel().select(user.getRole());
    }
    
    @FXML
    private void saveUser(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        User.Role role = cbbRole.getSelectionModel().getSelectedItem();
        
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || role == null) {
            AlertBox.errorAlert("You have not entered all the necessary information");
            return;
        }
        
        if (!am.checkIfEmailIsUsed(email)) {
            saveChanges(new User(firstName, lastName, email, password, role));            
        }       
        else if (selectedUser != null) {
            if (selectedUser.getEmail().equals(email)) {
                saveChanges(new User(firstName, lastName, email, password, role));
            }
            else AlertBox.errorAlert("Email is already used by another user");
            return;
        }
        else {
            AlertBox.errorAlert("Email is already used by another user");
        }
        
        
    }
    
    private void saveChanges(User user) {
        if (selectedUser == null) {
            am.addUser(user);
        }
        else am.updateUser(user);
        
        Stage stage = (Stage) btnSaveUser.getScene().getWindow();
        stage.close();
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    
    
}
