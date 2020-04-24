/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

/**
 *
 * @author fauxtistic
 */
public class AlertBox {   
            
    public static void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("ERROR");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setResizable(true);
        alert.setContentText(String.format(message));
        alert.showAndWait();
    }
    
}
