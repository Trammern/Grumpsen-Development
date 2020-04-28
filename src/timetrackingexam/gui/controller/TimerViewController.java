/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import timetrackingexam.gui.util.AlertBox;


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
    private Label Ttime;
    private Label Tmin;
     @FXML
    private JFXTextField TSek;

    static int millisek = 0;
    static int seconds = 55;
    static int minutes = 0;
    static int hours = 0;
    private boolean timerstart = true;
    @FXML
    private JFXTextField TMin;
    @FXML
    private JFXTextField TTime;
   
   
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    @FXML
    private void btnStopStart(ActionEvent event) throws InterruptedException {
        if (timeIsActive) {
            btnTimeButton.setText("Start");
            
            scheduler.startTimer(task1);
            
            timeIsActive = false;
            timerstart = true;
            
       }
        else{
            btnTimeButton.setText("Pause");
           timeIsActive = true;
           timerstart = false;
       }
  //      timeIsActive = false;
        
        Thread t = new Thread()
        {
           public void run()
            {
               
             for (;;)  
                 
              {
                   if (timerstart==true)
                    {
                      
                        try
                        {
                            sleep(1);
                            
                            if(millisek>1000)
                            {
                                millisek=0;
                                seconds++;
                                TSek.setText(" : " + seconds);
                           }
                            if(seconds==60)
                           {
                                millisek=0;
                                seconds=0;
                                minutes++;
                                TMin.setText(" : " + minutes);
                           }
                             if(minutes==60)
                           {
                                 millisek=0;
                                 seconds=0;
                                 minutes=0;
                                 hours++;
                                 TTime.setText(" : " + hours);
                           }
                            millisek++;
                            
                            
                            

                         
                             System.out.println(seconds);
                             
                             
                        }
                        catch (InterruptedException e)
                        {
                            AlertBox.errorAlert("Error");
                        }
                    }
                   
                   else
                   {
                      
                       break;
                   }
              }
              
            }
           
            
        };
       
          t.start();
           
    }

    }
}

    














        
      
   
    
    

