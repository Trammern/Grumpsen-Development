/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectsOverviewController implements Initializable {
    
    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    private TaskTime selectedTime;
    private boolean hasRun;

    @FXML
    private JFXComboBox<Project> cbbProjectSelect;
    @FXML
    private JFXListView<Task> lstTaskList;
    @FXML
    private Label txtSlectedTask;
    @FXML
    private JFXTextArea txtTaskDescription;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXButton btnAddTask;
    @FXML
    private JFXButton btnEditTask;
    @FXML
    private JFXButton btnDeleteTask;
    @FXML
    private Menu menuUser;
    @FXML
    private MenuItem menuItemPassword;
    @FXML
    private MenuItem menuItemAdmin;
    @FXML
    private JFXButton btnTimeButton;
    @FXML
    private Text txtTaskText;
    @FXML
    private Label txtSec;
    @FXML
    private Label txtMin;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private TextField fldSec;
    @FXML
    private TextField fldMin;
    @FXML
    private TextField fldHour;
    @FXML
    private Label txtHour;
    @FXML
    private MenuItem menuItemUser;
  
  
    @FXML
    private JFXButton btnNoneBillable;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        am = AppModel.getInstance();
        setNodes();
        hasRun = true;
        if (currentUser.getRole()!=User.Role.Admin) {
            menuItemAdmin.setDisable(true);
            menuItemAdmin.setVisible(false);
            menuItemUser.setDisable(true);
            menuItemUser.setVisible(false);
        }
        
        initTooltips();
        initEffects();
        
        
        
    } 
    
    private void initTooltips() {
        btnAddTask.setTooltip(TooltipFactory.create("Click here to create a new task for the selected project"));        
        btnEditTask.setTooltip(TooltipFactory.create("Click here to edit an existing task.\nSelect a task first"));
        btnDeleteTask.setTooltip(TooltipFactory.create("Click here to delete a task.\nSelect a task first"));
        btnTimeButton.setTooltip(TooltipFactory.create("Click here to start or pause registering the time you work on the task"));
        btnNoneBillable.setTooltip(TooltipFactory.create("Click here to start or pause Nonebillable time when working on this task"));
    }
    
    private void initEffects() {        
        NodeCustomizer.nodeEffect(btnAddTask);
        NodeCustomizer.nodeEffect(btnEditTask);
        NodeCustomizer.nodeEffect(btnDeleteTask);
        NodeCustomizer.nodeEffect(btnTimeButton);
        NodeCustomizer.nodeEffect(btnSubmit);        
    }
    
    @FXML
    private void addTask(ActionEvent event) {
        am.setCurrentTask(null);
        openAddEdit();
    }

    
    @FXML
    private void setItemsOnList(ActionEvent event) {
        am.setCurrentProject(cbbProjectSelect.getSelectionModel().getSelectedItem());        
        lstTaskList.setItems(am.getTasks());
        am.setCurrentProject(selectedProject);      
    }

    @FXML
    private void setCurrentTask(MouseEvent event) {
        if (lstTaskList.getSelectionModel().getSelectedItem() != null) {
            am.setCurrentTask(lstTaskList.getSelectionModel().getSelectedItem());
            txtSlectedTask.setText(am.getCurrentTask().getName());
            
        
        if (am.getCurrentTask().getDescription() == null)
        {
            txtTaskDescription.setStyle("-fx-font-style: italic;");
            txtTaskDescription.setText("No available description found for this task");
        }
        else
        {
            txtTaskDescription.setStyle("-fx-font-style: normal;");
            txtTaskDescription.setText(am.getCurrentTask().getDescription());
        } 
            
            if(am.getTime() != null){
                selectedTime = am.getTime();
                fldHour.setText(am.getTime().getHours()+ "");
                fldMin.setText(am.getTime().getMin()+ "");
                fldSec.setText(am.getTime().getSec()+ "");
            }
            else{
                fldHour.setText("0");
                fldMin.setText("0");
                fldSec.setText("0");
            }
        }
        
        
    }

    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }
    
    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.logout(primStage);
    }
    
    private void openAddEdit()
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/promts/AddTask.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("New Task");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleEditTask(ActionEvent event)
    {
        if (am.getCurrentTask() != null)
        {
            openAddEdit();
        }
    }

    @FXML
    private void handleDeleteTask(ActionEvent event)
    {
        Task selectedTask = lstTaskList.getSelectionModel().getSelectedItem();
        if (selectedTask != null && am.deleteTask())
        {
           am.fetch();
        }
        else
        {
            System.out.println("Please choose the task you want to delete");
        }
    }

    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }

    @FXML
    private void handleBarChart(ActionEvent event) {
        
        try
            {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/DiagramView.fxml"));
                Parent root1 = (Parent) fxml.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.showAndWait();
                stage.setTitle("Assigned Time For Tasks");
            
            } catch (IOException ex)
            {   
                Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void handlePieChart(ActionEvent event)
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/DiagramView.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initOwner((Stage) menuBar.getScene().getWindow());
            stage.showAndWait();
            stage.setTitle("Time used");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToAdmin(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectManagementView(primStage);
    }

    @FXML
    private void btnStopStart(ActionEvent event)
    {
        if(!am.timerIsRunning()||btnTimeButton.getText().equals("Start")){
            am.startTimer(fldSec, fldMin, fldHour);
            btnSubmit.setDisable(true);
            btnTimeButton.setText("Pause");
        }
        else if (btnTimeButton.getText().equals("Pause")){
            am.stopTimer();
            btnTimeButton.setText("Start");
            btnSubmit.setDisable(false);
        }
    }
    private void btnNoneBillable (ActionEvent event)
    {
        if(!am.timerIsRunning()||btnNoneBillable.getText().equals("None-Billable")){
            am.startTimer(fldSec, fldMin, fldHour);
            btnSubmit.setDisable(true);
            btnNoneBillable.setText("Pause");
        }
        else if (btnNoneBillable.getText().equals("Pause")){
            am.stopTimer();
            btnNoneBillable.setText("Start");
            btnSubmit.setDisable(false);
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event)
    {
        TaskTime tt = new TaskTime(
                am.getCurrentTask().getId(),
                currentUser.getId(),
                Integer.parseInt(fldSec.getText()),
                Integer.parseInt(fldMin.getText()),
                Integer.parseInt(fldHour.getText()),
                LocalDate.now()
        );
        
        am.submitTime(tt);
    }

    @FXML
    private void goToUserManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.userManagementView(primStage);
    }
        
    
    private void setNodes(){
        
        
        txtSlectedTask.setText("(Select Project)");
        cbbProjectSelect.setItems(am.getProjects());
        btnSubmit.setDisable(true);
        
        if(!hasRun){
            currentUser = am.getCurrentUser();
            am.setCurrentProject(cbbProjectSelect.getItems().get(0));
            selectedProject = am.getCurrentProject();
        }
        
        lstTaskList.setItems(am.getTasks());
        menuUser.setText(currentUser.getEmail());
    }


    @FXML
    private void handleNoneBillable(ActionEvent event) {
    }
     
}
    
    
    

