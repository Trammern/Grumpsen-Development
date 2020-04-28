/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author fauxtistic
 */
public interface ICommonMenuActions {
    
    default void openView(String viewPath, String title, Stage primStage, boolean closeCurrentView, boolean disableInputToOtherViews) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(viewPath));
            Scene scene = new Scene(loader);            
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);            
            if (closeCurrentView) {
                stage.show();
                primStage.close();
            }
            else {
                stage.initOwner(primStage);
                if (disableInputToOtherViews) {
                    stage.initModality(Modality.WINDOW_MODAL);
                }
                stage.show();
            }
            
        } catch (IOException e) {
            AlertBox.errorAlert("Could not open new window");
        }
    }
    
    default void logout(Stage primStage) {
        openView("/timetrackingexam/gui/view/Login.fxml", "Login as user", primStage, true, true);
    }
            
    
}
