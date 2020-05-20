/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class AddTaskController implements Initializable
{
   private AppModel am;
   private Task updateTask;

    @FXML
    private TextField txtAddTaskName;
    @FXML
    private TextArea txtAddTaskDescription;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        if (am.getCurrentTask() != null)
        {
            txtAddTaskName.setText(am.getCurrentTask().getName());
            txtAddTaskDescription.setText(am.getCurrentTask().getDescription());
        }
        System.out.println(am.getCurrentProject().toString());
    }    



    @FXML
    private void handleSaveAddTask(ActionEvent event)
    {
        if (am.getCurrentTask() != null)
        {
            editTask();
        }
        else
        {
            newTask();
        }
        
    }

    @FXML
    private void handleCancelTask(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    
    private void newTask()
    {
        Task newTask = new Task(
                am.getCurrentProject().getId(),
                am.getCurrentUser().getId(),
                txtAddTaskName.getText(),
                txtAddTaskDescription.getText()
               );
        am.addTask(newTask, am.getCurrentProject());
        
        Date created = new Date();
        newTask.setTaskCreated(created);

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    
    private void editTask()
    {
        Task updatedTask = new Task(
                txtAddTaskName.getText(),
                txtAddTaskDescription.getText());
        am.updateTask(updatedTask);
        
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        
    }
    
}
