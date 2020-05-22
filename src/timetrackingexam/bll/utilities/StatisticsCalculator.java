/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.utilities;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;


        
/**
 * This class is responsible for doing all the maths behind the diagrammes
 * @author math2
 */
public class StatisticsCalculator {
    
    private int lcIndex = 0;
    private int lineChartGrowth;
    
    
    /**
     * This method calculates the growth in time, over time. each time it is called
     * the index goes up and it adds the integer to the sum of time growth
     * @param currentTask the task you want the statistics of
     * @return a long representing the incrementing time
     */
    public int timeGrowth(ObservableList<Task> tasks){
        return tasks.get(lcIndex).getTotalTimeUsed().getHours();
    }
    
    public int getHoursUsed(Task currentTask){
        return currentTask.getTotalTimeUsed().getHours();
    }
}
