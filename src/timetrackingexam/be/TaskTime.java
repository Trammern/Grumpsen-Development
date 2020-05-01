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

    public TaskTime(int sec, int min, int hours) {
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        dateOfWeek = LocalDate.now();
    }
    
    public LocalDate getDateOfWeek() {
        return dateOfWeek;
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
    
    public long getTotalSeconds(){
        long totalSeconds = 0;
        
        totalSeconds = Math.round(sec + min * 60 + hours * Math.pow(60, 2));
        
        return totalSeconds;
    }
    
}