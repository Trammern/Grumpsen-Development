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
public class ViewGuide {
    
    public static final String LOGIN_VIEW_PATH = "/timetrackingexam/gui/view/Login.fxml";
    public static final String LOGIN_VIEW_TITLE = "Login as user";
    public static final String PASSWORD_VIEW_PATH = "/timetrackingexam/gui/view/ChangePasswordView.fxml";
    public static final String PASSWORD_VIEW_TITLE = "Change your password";
    public static final String PROJECTS_OVERVIEW_PATH = "/timetrackingexam/gui/view/ProjectsOverview.fxml";
    public static final String PROJECTS_OVERVIEW_TITLE = "Projects Overview";
    public static final String PROJECT_MANAGEMENT_PATH = "/timetrackingexam/gui/view/ProjectManagementView.fxml";
    public static final String PROJECT_MANAGEMENT_TITLE = "Project Management View";
    
    public static void openView(String viewPath, String title, Stage primStage, boolean closeCurrentView, boolean disableInputToOtherViews) {
        try {
            Parent loader = FXMLLoader.load(ViewGuide.class.getResource(viewPath));
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
                    stage.showAndWait();
                }
                else {
                    stage.show();
                }
                                
            }
            
        } catch (IOException e) {
            AlertBox.errorAlert("Could not open new window");
        }
    }
    
    public static void logout(Stage primStage) {
        openView(LOGIN_VIEW_PATH, LOGIN_VIEW_TITLE, primStage, true, true);
    }
    
    public static void changePasswordView(Stage primStage) {
        openView(PASSWORD_VIEW_PATH, PASSWORD_VIEW_TITLE, primStage, false, true);
    }
    
    public static void projectsOverview(Stage primStage) {
        openView(PROJECTS_OVERVIEW_PATH, PROJECTS_OVERVIEW_TITLE, primStage, true, true);
    }
    
    public static void projectManagementView(Stage primStage) {
        openView(PROJECT_MANAGEMENT_PATH, PROJECT_MANAGEMENT_TITLE, primStage, true, true);
    }
    
}
