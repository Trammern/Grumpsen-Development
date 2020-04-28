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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import timetrackingexam.bll.threads.Scheduler;
import timetrackingexam.bll.threads.TimerRunnable;


/**
 * FXML Controller class
 * 
 * @author math2
 */
public class TimerViewController implements Initializable {

    private boolean timeIsActive = true;
    private TimerRunnable task1;
    private Scheduler scheduler;
    
    @FXML
    private Text txtTaskText;
    @FXML
    private JFXButton btnTimeButton;
    @FXML
    private AnchorPane anchorPane;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    /**
     * Starts the timer on the gui whilst also swithing button text
     * @param event the event which triggers the timer
     */
    @FXML
    private void btnStopStart(ActionEvent event){
        if(timeIsActive){
            toggleBtnProperties();
            task1 = new TimerRunnable(tHour, tMin, tSec);
            scheduler = new Scheduler();
            scheduler.startTimer(task1);
        }
        else{
            toggleBtnProperties();
            scheduler.pause();
        }

    }

    /**
     * Shuts down the timer and creates a new task that updates or replaces
     * previous task
     * @param event the event which triggers a submition
     */
    @FXML
    private void handleSubmit(ActionEvent event) {
        if(scheduler!=null&&timeIsActive){
            scheduler.stop();
            System.exit(0);
        }
        
    }
    
    /**
     * Toggles all the buttons' properties and setting the active time
     */
    private void toggleBtnProperties(){
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
    
    }


    














        
      
   
    
    

