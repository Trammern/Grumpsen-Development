/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.time.LocalDate;

/**
 *
 * @author math2
 */
public class TaskTime {
    private int sec;
    private int min;
    private int hours;
    private LocalDate dateOfWeek;
    private long totalMinutes = 0;

    public TaskTime(int sec, int min, int hours) {
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        dateOfWeek = LocalDate.now();
    }
    /*
     * For Testing
     */
    public TaskTime(int sec, int min, int hours, LocalDate dateOfWeek) {
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        this.dateOfWeek = dateOfWeek;
    }
    
    
    
    public String getDateOfWeek() {
        return dateOfWeek.toString();
    }

    public void setDateOfWeek(LocalDate dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
    
   
    
}
