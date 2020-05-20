/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.bll.security.LoginTools;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertBox;

/**
 *
 * @author Rizvan
 */
public class Login implements Initializable
{

    private AppModel appModel;
    
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private ImageView LoginImageView;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       appModel = AppModel.getInstance();
       appModel.setCurrentUser(null);
       appModel.setCurrentProject(null);
       appModel.setCurrentTask(null);
       appModel.setSelectedUser(null);
       initKeys();

    }    
    
    private void initKeys() {
        actionOnEnterKey(txtPassword);
        actionOnEnterKey(txtName);
    }
    
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
        login();
    }    
        
    public void login() {
        String email = txtName.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            AlertBox.errorAlert("The input fields must be filled out");
        } else {
            User user = LoginTools.getVerifiedUser(email, password, appModel.getAllUsers());

            if (user != null) {
                appModel.setCurrentUser(user);

                switch (appModel.getCurrentUser().getRole()) {
                    case Default:
                        openView("/timetrackingexam/gui/view/ProjectsOverview.fxml", "Projects Overview");
                        break;
                    case Admin:
                        openView("/timetrackingexam/gui/view/ProjectManagementView.fxml", "Project Management View");
                        break;
                    default:
                        AlertBox.errorAlert("No view defined for this role");
                }
            } else {
                AlertBox.errorAlert("Email or password incorrect");
            }
        }
        txtPassword.clear();
    }
    
    private void openView(String viewPath, String title) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(viewPath));
            Scene scene = new Scene(loader);
            Stage primStage = (Stage) btnLogin.getScene().getWindow();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
            primStage.close();
        } catch (IOException e) {
            AlertBox.errorAlert("Could not open new window");
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }   
       
        
}
