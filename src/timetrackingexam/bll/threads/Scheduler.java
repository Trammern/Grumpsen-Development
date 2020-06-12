
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
public class Scheduler{

    private final BlockingQueue<TimerRunnable> QUEUE = new LinkedBlockingQueue<>();

    private TimerRunnable currentTimer = null;
    private ExecutorService executor = null;

    /**
     * Starts the current timer
     * @param timer the timer to be startet
     */
    public synchronized void startTimer(TimerRunnable timer) {
            currentTimer = timer;
            currentTimer.start();
    }

    /**
     * Clears the queue and stops the thread
     */
    public synchronized void stop() {
        QUEUE.forEach((runnable) -> {
            QUEUE.remove(runnable);
        });
            currentTimer.stop();
        
    }
}
