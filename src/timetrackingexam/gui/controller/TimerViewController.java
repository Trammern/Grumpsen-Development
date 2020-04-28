/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private ExecutorService executor;
    private TimerRunnable task1;
    private Scheduler scheduler;
    
    @FXML
    private Text txtTaskText;
    @FXML
    private JFXButton btnTimeButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label Ttime;
    @FXML
    private Label Tmin;
    @FXML
    private Label TSec;

    static int millisek = 1;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    private boolean timerstart = true;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        executor = Executors.newSingleThreadExecutor();
        
        task1 = new TimerRunnable(TSec, Tmin, Ttime);
    }    

    @FXML
    private void btnStopStart(ActionEvent event) throws InterruptedException {
        if (timeIsActive) {
            btnTimeButton.setText("Start");
            
            scheduler.startTimer(task1);
            
            timeIsActive = false;
        } else {
            btnTimeButton.setText("Pause");
            timeIsActive = true;
        }
        timeIsActive = false;

    }
}

    














        
      
   
    
    

