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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author math2
 */
public class TimerViewController implements Initializable {

    private boolean timeIsActive = true;
    
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
    private Label TSek;

    static int millisek = 0;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    private boolean timerstart = true;
   
    
    
   
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
        timeIsActive = false;
        
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
                           }
                            else if(seconds>60)
                           {
                                millisek=0;
                                seconds=0;
                                minutes++;
                           }
                            else if(minutes>60)
                           {
                                 millisek=0;
                                 seconds=0;
                                 minutes=0;
                                 hours++;
                           }
                            TSek.setText(" : " + seconds);
                            millisek++;
                             Tmin.setText(" : "+minutes);
                             Ttime.setText(" : "+hours);
                             System.out.println(millisek);
                             
                        }
                        catch (InterruptedException e)
                        {
                            
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

    














        
      
   
    
    

