/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

/**
 *
 * @author fauxtistic
 */
public class AlertFactory {   
            
    public static void showErrorAlert(String message) {
        Alert alert = createAlert("Error Dialog", "ERROR", message, Alert.AlertType.ERROR);        
        alert.showAndWait();
    }
    
    public static Alert createConfirmationAlert(String message) {
        return createAlert("Confirm action", null, message, Alert.AlertType.CONFIRMATION);
    } 
    
    public static Alert createAlert(String title, String header, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setResizable(true);
        alert.setContentText(String.format(message));
        return alert;
    }
    
}
