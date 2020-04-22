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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private ImageView LoginImageView;
    @FXML
    private Button handleLogin;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       

    }    
    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {

        {
       
      if (txtName.getText().equals("user") 
          && txtPassword.getText().equals("123"))
          try {
            Parent loader = FXMLLoader.load(getClass().getResource("/timetrackingexam/gui/controller/TimeGrowth.fxml"));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
          }
        catch(IOException e) 
        {
        }
     
    }

        String email = txtName.getText().trim();
        String password = txtPassword.getText();
        
        if (email.isEmpty() || password.isEmpty())
        {
            errorAlert("The input fields must be filled out");
        }
         

    }

    
    
    private void getVerifiedUser(String email, String password)
    {
        List<User> users = appModel.getAllUsers();
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
