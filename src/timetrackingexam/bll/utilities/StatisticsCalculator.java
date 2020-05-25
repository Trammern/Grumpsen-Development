/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
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
    private ObservableList<Task> tasks;
    private ITimeTrackBLL dBFacade;
    private Project currentProject;

    public StatisticsCalculator(Project p) {
        currentProject = p;
        dBFacade = new TimeTrackBLLFacade();
    }
    
    
    
    /**
     * This method calculates the growth in time, over time. each time it is called
     * the index goes up and it adds the integer to the sum of time growth
     * @return a long representing the incrementing time
     */
    public XYChart.Series timeGrowth(){
        
        tasks = dBFacade.getTasks(currentProject);
        ObservableList<TaskTime> times = FXCollections.observableArrayList();
        
        XYChart.Series series = new XYChart.Series();
        for (Task task : tasks) {
            times.addAll(dBFacade.getTime(task));
        }
        int growth = 0;
        for (TaskTime time : times) {
            growth += time.getHours();
            series.getData().add(new XYChart.Data(
                    time.getDate().toString(),
                    growth));
        }
        return series;
    }
    
    public ObservableList<PieChart.Data> getHoursPerTaskUsed(){
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Task task : tasks) {
            pieChartData.add(new PieChart.Data(task.getName(), totalHours(task)));
        }
        return pieChartData;
    }
    
    public int totalHours(Task t){
        
        ObservableList<TaskTime> times = FXCollections.observableArrayList();
        int totalHour = 0;
        times.addAll(dBFacade.getTime(t));
        
        for (TaskTime time : times) {
            totalHour += time.getHours();
        }
        
        return totalHour;
    }
}
