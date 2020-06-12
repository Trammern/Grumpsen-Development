
package timetrackingexam.bll.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.TextField;

/**
 * This is the class responsible for counting the time
 * @author math2
 */
public class TimerRunnable implements Runnable{

    private final int DELAY = 1;    
    private TextField tSec;
    private TextField tMin;
    private TextField tHours;    
    private boolean active;
    private ExecutorService executor;
    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;

    public TimerRunnable(TextField lSec, TextField lMin, TextField lHour) {
        this.tSec = lSec;
        this.tMin = lMin;
        this.tHours = lHour;
    }
    
    /**
     * The methods used by the executor service.
     * It counts the time on a sepparate thread.
     */
    @Override
    public void run() {
        while (active) {
                
                tSec.setText(seconds+"");
                tMin.setText(minutes+"");
                tHours.setText(hours+"");
                
                seconds++;
                
                try {
                    TimeUnit.SECONDS.sleep(DELAY);
                } 
                catch (InterruptedException ex) {
                }

                if (seconds >= 59) {
                    seconds = -1;
                    minutes++;
                }

                if (minutes >= 59) {
                    seconds = -1;
                    minutes = 0;
                    hours++;
                }
        }
    }
    
    /**
     * Checks if the thread is running
     * @return the threads status
     */
    public boolean isActive(){
        return active;
    }
    
    /**
     * submits this class to the ExecutorService, which runs it.
     * also sets is running to true.
     */
    public void start() {
        
        convertLabelsToInt();
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this);
        active = true;
             
        tSec.setEditable(false);
        tMin.setEditable(false);
        tHours.setEditable(false);
    }
    
    /**
     * shuts down the whole executorService.
     */
    public void stop(){
        executor.shutdown();
        active = false;
        
        tSec.setEditable(true);
        tMin.setEditable(true);
        tHours.setEditable(true);
    }
    
    /**
     * Used to make sure that at String won't crash the thread.
     */
    private void convertLabelsToInt(){
            String sec = tSec.getText();
            sec = sec.replaceAll("[^0-9\\s+]", "");
            seconds = Integer.parseInt(sec.trim());
            
            String min = tMin.getText();
            min = min.replaceAll("[^0-9\\s+]", "");
            minutes = Integer.parseInt(min.trim());
            
            String hour = tHours.getText();
            hour = hour.replaceAll("[^0-9\\s+]", "");
            hours = Integer.parseInt(hour.trim());
    }

    
    
    
    
    
    
}
