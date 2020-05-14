/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.threads;

import javafx.scene.control.TextField;
import timetrackingexam.bll.threads.Scheduler;
import timetrackingexam.bll.threads.TimerRunnable;

/**
 *
 * @author math2
 */
public class ThreadManager {
    
    private Scheduler scheduler;
    
    public void startTimer(TextField sec, TextField min, TextField hours){
        
        scheduler = new Scheduler();
        TimerRunnable tr = new TimerRunnable(sec, min, hours);
        scheduler.startTimer(tr);
        
    }
    
    public boolean isRunning(){
        if (scheduler==null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void pauseTimer(){
        scheduler.pause();
    }
    
    public void stopTimer(){
        scheduler.stop();
    }
}
