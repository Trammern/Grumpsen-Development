/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author math2
 */
public class Scheduler implements Runnable{
    
    private TimerRunnable currentTimer = null;
    private BlockingQueue<TimerRunnable> queue = new LinkedBlockingQueue<>();
    private ExecutorService executor = null;

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                System.out.println(queue.toString());
            }
        }
    }
     
        public void startTimer(TimerRunnable timer){
            if (executor == null || executor.isShutdown()) {
            executor = Executors.newSingleThreadExecutor();
            executor.submit(this);
            System.out.println("Executor was created");
        }
        
        if(currentTimer == null && queue.isEmpty()){
            currentTimer = timer;
            currentTimer.start();
        }
        
        else{
            try{
                queue.put(currentTimer);
                System.out.println("Executor was used");
            }
            catch(InterruptedException iEx){
                System.out.println("Slideshow was stopped");
            }
        }
        }
    }
    
    

