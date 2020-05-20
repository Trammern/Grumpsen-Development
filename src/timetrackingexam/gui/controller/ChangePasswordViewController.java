/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.bll.security.LoginTools;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class ChangePasswordViewController implements Initializable {

    private AppModel am;
    private User user;
    
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private PasswordField txtOldPass;
    @FXML
    private PasswordField txtNewPass;
    @FXML
    private PasswordField txtNewPass2;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        user = am.getCurrentUser();
        initKeys();
    }    
    
    private void initKeys() {
        actionOnEnterKey(txtOldPass);
        actionOnEnterKey(txtNewPass);
        actionOnEnterKey(txtNewPass2);
    }
    
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                changePassword();
            }
        });
    }

    @FXML
    private void savePassword(ActionEvent event) {
         changePassword();        
                        
    }
    
    private void changePassword() {
        String oldPassword = txtOldPass.getText();
        String newPassword = txtNewPass.getText();
        String repeatedNewPassword = txtNewPass2.getText();
        
        if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatedNewPassword.isEmpty()) {
            AlertBox.errorAlert("All input fields must be filled out");
            return;
        }
        if (!newPassword.equals(repeatedNewPassword)) {
            AlertBox.errorAlert("The text in the two fields where you have entered the new password do not match");
            txtNewPass.clear();
            txtNewPass2.clear();
            return;
        }
        String verifiedNewPassword = LoginTools.getVerifiedNewPassword(user, oldPassword, newPassword);
        if (verifiedNewPassword != null) {
            user.setPassword(verifiedNewPassword);
            am.updateUser(user);
            //am.getAllUsers(); //necessary?
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        }
        else {
            AlertBox.errorAlert("The password you have entered in the first field is incorrect");
            txtOldPass.clear();            
        }      
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
}
