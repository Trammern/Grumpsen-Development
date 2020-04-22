/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

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
       

    }    

    @FXML
    private void handleLogin(ActionEvent event)
    {
        login();
    }
    
    public void login()
    {
        String email = txtName.getText().trim();
        String password = txtPassword.getText();
        
        if (email.isEmpty() || password.isEmpty())
        {
            errorAlert("The input fields must be filled out");
        }
        else if(getVerifiedUser(email, password)!=null)
        {
            appModel.setCurrentUser(getVerifiedUser(email, password));
            //Open new view
            
        }
        
        
        else errorAlert("Email or password incorrect");
        txtPassword.clear();
    }
    
    
    private User getVerifiedUser(String email, String password)
    {
        List<User> users = appModel.getAllUsers();
        
        for (User user : users)
        {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
            {
                appModel.setCurrentUser(user);
                System.out.println("Successfully logged in as " + user.getFirstName() + ". Your role is " + user.getRole());
                return user;
            }
        }
        
        return null;
    }
    
    private void errorAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);        
        alert.setTitle("Error Dialog");
        alert.setHeaderText("ERROR");
        alert.setContentText(String.format(message));
        alert.showAndWait();
    }
    
    
    
    
}
