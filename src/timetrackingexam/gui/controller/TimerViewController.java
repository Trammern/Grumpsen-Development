/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.gui.model.AppModel;


/**
 * FXML Controller class
 * 
 * @author math2
 */
public class TimerViewController implements Initializable {

    private boolean timeIsActive = true;
    private AppModel am;
    private Task currentTask;
    private int seconds;
    private int minutes;
    private int hours;
    
    @FXML
    private Text txtTaskText;
    @FXML
    private JFXButton btnTimeButton;
    @FXML
    private Label tHour;
    @FXML
    private Label tMin;
    @FXML
    private Label tSec;
    @FXML
    private JFXButton btnSubmit;

   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        txtTaskText.setText(am.getCurrentTask().getName());
        setCurrentTask();
        
        
    }    
    /**
     * Starts the timer on the gui whilst also swithing button text
     * @param event the event which triggers the timer
     */
    @FXML
    private void btnStopStart(ActionEvent event){
        if(timeIsActive){
            toggleBtnProperties();
            am.startTimer(tSec, tMin, tHour);
        }
        else{
            toggleBtnProperties();
            am.pauseTimer();
        }

    }

    /**
     * Shuts down the timer and creates a new task that updates or replaces
     * previous task
     * @param event the event which triggers a submition
     */
    @FXML
    private void handleSubmit(ActionEvent event) {
        if(timeIsActive){
            am.stopTimer();
            convertLabelsToInteger();
            am.getCurrentTask().setTimeUsed(new TaskTime(seconds, minutes, hours));
            Stage primStage = (Stage) tHour.getScene().getWindow();
            primStage.close();
        }
        
    }
    
    /**
     * Toggles all the buttons' properties and setting the active time
     */
    private void toggleBtnProperties(){
        
        Stage stage = (Stage) txtTaskText.getScene().getWindow();
        stage.setOnHiding((WindowEvent event) -> {
            am.stopTimer();
        });
        
        if(timeIsActive){
            btnSubmit.setDisable(timeIsActive);
            btnTimeButton.setText("Pause");
            timeIsActive = false;
        }
        else{
            btnSubmit.setDisable(timeIsActive);
            btnTimeButton.setText("Start");
            timeIsActive = true;
        }
    }
    
    private void setCurrentTask(){
        currentTask=am.getCurrentTask();
        
        if(currentTask.getTimeUsed()!=null){
            TaskTime tt = currentTask.getTimeUsed();
            
            tSec.setText("sec: " + tt.getSec());
            tMin.setText("min: " + tt.getMin());
            tHour.setText("hours: " + tt.getHours());
        }
    }
    
    private void convertLabelsToInteger(){
        String sec = tSec.getText();
            sec = sec.replaceAll("[^0-9\\s+]", "");
            seconds = Integer.parseInt(sec.trim());
            
            String min = tMin.getText();
            min = min.replaceAll("[^0-9\\s+]", "");
            minutes = Integer.parseInt(min.trim());
            
            String hour = tHour.getText();
            hour = hour.replaceAll("[^0-9\\s+]", "");
            hours = Integer.parseInt(hour.trim());
    }
}


    














        
      
   
    
    

