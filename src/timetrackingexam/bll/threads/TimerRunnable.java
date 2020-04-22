/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.threads;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;
    private boolean active;
    private int timeStamp;
    private LocalTime elapsedTime;
    /**
     *
     */
    @Override
    public void run() {
        try {
            while(active){
                timeStamp++;
                System.out.println(timeStamp);
                TimeUnit.SECONDS.sleep(DELAY);
            }
        } catch (InterruptedException ex) {
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
}
