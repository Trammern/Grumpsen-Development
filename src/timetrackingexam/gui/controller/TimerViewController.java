/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import timetrackingexam.bll.threads.TimerRunnable;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class TimerViewController implements Initializable {

    private boolean timeIsActive = true;
    
    @FXML
    private Text txtTime;
    @FXML
    private Text txtTaskText;
    @FXML
    private JFXButton btnTimeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnStopStart(ActionEvent event) throws InterruptedException {
        if(timeIsActive){
            btnTimeButton.setText("Start");
            timeIsActive = false;
        }
        else{
            btnTimeButton.setText("Pause");
            timeIsActive = true;
        }
    }
    
}
