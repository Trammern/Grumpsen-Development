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
    // Starts the timer of a task
    public void startTimer(TextField sec, TextField min, TextField hours){
        
        scheduler = new Scheduler();
        TimerRunnable tr = new TimerRunnable(sec, min, hours);
        scheduler.startTimer(tr);
        
    }
    // Checks if the task is being counted
    public boolean isRunning(){
        if (scheduler==null){
            return false;
        }
        else{
            return true;
        }
    }
    // Pause the timer
    public void pauseTimer(){
        scheduler.pause();
    }
    // Stops the timer
    public void stopTimer(){
        scheduler.stop();
    }
}
