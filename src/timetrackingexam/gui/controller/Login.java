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
import timetrackingexam.gui.util.AlertFactory;

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
    
    /**
     * Initializes actions on enter keystroke for nodes
     */
    private void initKeys() {
        actionOnEnterKey(txtPassword);
        actionOnEnterKey(txtName);
    }
    
    /**
     * Sets action on using Enter key for node
     * Currently, when pressing enter, an attempt is made to login
     * @param node to apply action on enter keystroke for
     */
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
    }

    /**
     * Attempts to login
     * @param event 
     */
    @FXML
    private void handleLogin(ActionEvent event)
    {
        login();
    }    
        
    /**
     * Checks if email and password entered in text fields match those of a user in list of all users
     * If such a user is found, sets currentUser variable to this user and opens new view depending on whether or not user has admin privileges
     * If text fields are not filled out, or if no user is found, will notify with appropriate alert box
     */
    public void login() {
        String email = txtName.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            AlertFactory.showErrorAlert("The input fields must be filled out");
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
                        AlertFactory.showErrorAlert("No view defined for this role");
                }
            } else {
                AlertFactory.showErrorAlert("Email or password incorrect");
            }
        }
        txtPassword.clear();
    }
    
    /**
     * Opens a new view and closes the currently displaying one
     * @param viewPath path to .fxml file
     * @param title of new view
     */
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
            AlertFactory.showErrorAlert("Could not open new window");
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }   
       
        
}
