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
import javafx.scene.control.TextField;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class ChangePasswordViewController implements Initializable {

    @FXML
    private TextField txtOldPass;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private TextField txtNewPass;
    @FXML
    private TextField txtNewPass2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppModel am = AppModel.getInstance();
        User user = am.getCurrentUser();
    }    

    @FXML
    private void savePassword(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
