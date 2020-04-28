/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import javafx.scene.control.Label;
import timetrackingexam.bll.threads.Scheduler;
import timetrackingexam.bll.threads.TimerRunnable;

/**
 *
 * @author math2
 */
public class ThreadManager {
    
    private Scheduler scheduler;
    
    
    public void startTimer(Label sec, Label min, Label hours){
        
        scheduler = new Scheduler();
        scheduler.startTimer(new TimerRunnable(sec, min, hours));
    }
    
    public void pauseTimer(){
        scheduler.pause();
    }
    
    public void stopTimer(){
        scheduler.stop();
    }
}
