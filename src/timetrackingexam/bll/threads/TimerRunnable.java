/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.threads;

import static java.lang.Thread.sleep;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;
    private boolean active;
    private ExecutorService executor;
    
    private final Label lSec;
    private final Label lMin;
    private final Label lHour;
    
    static int millisek = 1;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;

    public TimerRunnable(Label lSec, Label lMin, Label lHour) {
        this.lSec = lSec;
        this.lMin = lMin;
        this.lHour = lHour;
    }
    
    @Override
    public void run() {
   
        while (true) {
            Platform.runLater(() -> {
                seconds++;
                lSec.setText("Sec: " +seconds);
                lMin.setText("Min: " +minutes);
                lHour.setText("Hours: " +hours);
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
    
    

    
    
    
    void start() {
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this);
        System.out.println("Thread started");
    }

    void stop() {
        executor.shutdownNow();
        System.out.println("Thread stopped");
    }
    
    
}
