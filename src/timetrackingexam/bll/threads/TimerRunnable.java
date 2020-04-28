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
import javafx.scene.control.Label;

/**
 *
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;
    private boolean active;
    private int timeStamp;
    private LocalTime elapsedTime;
    private ExecutorService executor;
    
    private Label lSec;
    private Label lMin;
    private Label lHour;
    
    static int millisek = 1;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;

    public TimerRunnable(Label lSec, Label lMin, Label lHour) {
        this.lSec = lSec;
        this.lMin = lMin;
        this.lHour = lHour;
    }
    
    
    
    /**
     *
     */
    @Override
    public void run() {
        try {
            while (active) {

                for (;;) {
                    System.out.println(seconds);

                    sleep(1);

                    if (millisek > 1000) {
                        millisek = 0;
                        seconds++;
                    } else if (seconds > 60) {
                        millisek = 0;
                        seconds = 0;
                        minutes++;
                    } else if (minutes > 60) {
                        millisek = 0;
                        seconds = 0;
                        minutes = 0;
                        hours++;
                    }
                            lSec.setText(" : "+seconds);
                            millisek++;
                            lMin.setText(" : "+minutes);
                            lHour.setText(" : "+hours);

                }
            }
        } catch (InterruptedException ex) {
        }   
    
    }
    
    public void setActive(boolean active) {
        this.active = active;
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
