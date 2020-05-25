/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.bll.facade.ITimeTrackBLL;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.task.ITaskManager;
import timetrackingexam.bll.task.TaskManager;




        
/**
 * This class is responsible for doing all the maths behind the diagrammes
 * @author math2
 */
public class StatisticsCalculator {
    
    private int lcIndex = 0;
    private int lineChartGrowth;
    private ObservableList<TaskTime> times;
    private ObservableList<Project> projects;
    private ObservableList<Task> tasks;
    private ITimeTrackBLL dBFacade;

    public StatisticsCalculator(Project p) {
        
        dBFacade = new TimeTrackBLLFacade();
        tasks = dBFacade.getTasks(p);
    }
    
    
    
    /**
     * This method calculates the growth in time, over time. each time it is called
     * the index goes up and it adds the integer to the sum of time growth
     * @param currentTask the task you want the statistics of
     * @return a long representing the incrementing time
     */
    public XYChart.Series timeGrowth(){
        
        XYChart.Series series = new XYChart.Series();
        for (Task task : tasks) {
            times.addAll(dBFacade.getTime(task));
        }
        
        for (TaskTime time : times) {
            series.getData().add(new XYChart.Data(
                    time.getDate().toString(),
                    time.getHours()));
        }
            
        return series;
    }
    
    public int getHoursUsed(Task currentTask){
        return currentTask.getTotalTimeUsed().getHours();
    }
}
