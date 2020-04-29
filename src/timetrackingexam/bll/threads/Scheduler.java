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

/**
 * This class handles all the threads used by the program
 * It will probably have more threads to manage in the future
 * @author math2
 */
public class Scheduler implements Runnable {

    private final BlockingQueue<TimerRunnable> QUEUE = new LinkedBlockingQueue<>();

    private TimerRunnable currentTimer = null;
    private ExecutorService executor = null;

    
    @Override
    public void run() {
    }

    /**
     * Starts the current timer
     * @param timer the timer to be startet
     */
    public synchronized void startTimer(TimerRunnable timer) {
        if (executor == null || executor.isShutdown()) {
            executor = Executors.newSingleThreadExecutor();
            executor.submit(this);
            System.out.println("Executor was created");
        }

        if (currentTimer == null && QUEUE.isEmpty()) {
            currentTimer = timer;
            currentTimer.start();
        } else {
            try {
                QUEUE.put(currentTimer);
                System.out.println("Executor was used");
            } catch (InterruptedException iEx) {
                System.out.println("Timer was stopped");
            }
        }
    }

    /**
     * pauses the Thread
     */
    public synchronized void pause() {
        currentTimer.stop();
    }

    /**
     * Clears the queue and stops the thread
     */
    public synchronized void stop() {
        if(executor.isShutdown()){
            QUEUE.clear();
            currentTimer.stop();
            executor.shutdownNow();
        }
    }
}