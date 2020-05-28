/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.utilities;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
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
    private ObservableList<TaskTime> times;
    

    public StatisticsCalculator(Project p) {
        currentProject = p;
        dBFacade = new TimeTrackBLLFacade();
        tasks = dBFacade.getTasks(currentProject);
    }
    
    
    
    /**
     * This method calculates the growth in time, over time. each time it is called
     * the index goes up and it adds the integer to the sum of time growth
     * @return a long representing the incrementing time
     */
    public XYChart.Series timeUsedPerDay(){
        
        setTime();
        XYChart.Series series = new XYChart.Series<>();
        
        for (TaskTime time : times) {
            
            series.getData().add(new XYChart.Data(
                    time.getDate().toString(),
                    time.getHours()));
        }
        return series;
    }
    
    public XYChart.Series timeUsedPerWeek(){
        
        setTime();
        XYChart.Series series = timeUsedPerDay();
        List<XYChart.Data> datas = series.getData();
        
        XYChart.Series weekSeries = new XYChart.Series();
        
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        int weekNumber = LocalDate.parse(
                datas.get(0).getXValue().toString()).get(weekFields.weekOfWeekBasedYear());
        int total = 0;
        
        for (int i = 0; i < datas.size(); i++) {
            int newWeekNumber = LocalDate.parse(datas.get(i).getXValue().toString()).get(weekFields.weekOfWeekBasedYear());
            
            if(newWeekNumber == weekNumber){
                total += Integer.parseInt(datas.get(i).getYValue().toString());
                datas.get(i).setXValue("Week " + newWeekNumber);
                System.out.println(total);
                weekNumber = newWeekNumber;
            }
            
            else{
                weekNumber = LocalDate.parse(
                datas.get(i).getXValue().toString()).get(weekFields.weekOfWeekBasedYear());
                
                weekSeries.getData().add(new XYChart.Data<>("Week " + weekNumber,
                        total));
                total = 0;
            }
            
        }
        return weekSeries;
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
    
    private void setTime(){
        times = FXCollections.observableArrayList();
        
        XYChart.Series series = new XYChart.Series();
        for (Task task : tasks) {
            times.addAll(dBFacade.getTime(task));
        }
        
        times.sort((arg, arg1) -> {
            return (arg.getDate().getDayOfYear() - arg1.getDate().getDayOfYear());
        });
    }
}
