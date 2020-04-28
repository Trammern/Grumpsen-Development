/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * This is the class responsible for counting the time
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;    
    private final Label T_SEC;
    private final Label T_MIN;
    private final Label T_HOUR;
    
    private boolean active;
    private ExecutorService executor;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;

    public TimerRunnable(Label lSec, Label lMin, Label lHour) {
        this.T_SEC = lSec;
        this.T_MIN = lMin;
        this.T_HOUR = lHour;
    }
    
    /**
     * Counts the seconds, minutes, and hours gone by since the thread was started
     */
    @Override
    public void run() {
   
        while (active) {
            Platform.runLater(() -> {
                seconds++;
                T_SEC.setText("Sec: " +seconds);
                T_MIN.setText("Min: " +minutes);
                T_HOUR.setText("Hours: " +hours);
                });
            
                try {
                    TimeUnit.SECONDS.sleep(DELAY);
                } 
                catch (InterruptedException ex) {
                }

                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }

                if (minutes == 60) {
                    seconds = 0;
                    minutes = 0;
                    hours++;
                }
        }
    }
    
    

    
    
    /**
     * submits the thread to be executed
     */
    public void start() {
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this);
        active = true;
        System.out.println("Thread started");
    }
    
    /**
     * shuts down the executor and resets the labels
     */
    public void stop(){
        executor.shutdownNow();
        active = false;
        System.out.println("Thread stopped");
        
        T_SEC.setText("Sec: " +seconds);
        T_MIN.setText("Min: " +minutes);
        T_HOUR.setText("Hours: " +hours);
        
    }
    
    
    
    
    
}
