/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.time.LocalTime;

/**
 *
 * @author math2
 */
public class Task {
    private LocalTime timeUsed;
    private String name;
    private int employeeId;
    private int taskId;

    public Task(LocalTime timeUsed, String name, int employeeId, int taskId) {
        this.timeUsed = timeUsed;
        this.name = name;
        this.employeeId = employeeId;
        this.taskId = taskId;
    }

    public LocalTime getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(LocalTime timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
