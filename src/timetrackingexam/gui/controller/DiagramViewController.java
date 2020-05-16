/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author tramm
 */
public class DiagramViewController implements Initializable
{

    private final AppModel am = AppModel.getInstance();
    private static final DecimalFormat DF2 = new DecimalFormat("#.##");
    private Task currentTask;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    
    @FXML
    private BorderPane chartPane;
    @FXML
    private JFXButton btnNavigateBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currentTask = am.getCurrentTask();
        chartPane.setCenter(buildLineChart(currentTask));
    }    

    @FXML
    private void handleNavigateBack(ActionEvent event)
    {
        Stage stage = (Stage) btnNavigateBack.getScene().getWindow();
        stage.close();
    }
    
    private void buildBarChart(){
        
        
        
        xAxis = new CategoryAxis();
        xAxis.setLabel("Tasks");
        
        yAxis = new NumberAxis(0, 40, 2);
        yAxis.setLabel("Time in Hours");
        
        BarChart barChart = new BarChart<>(xAxis, yAxis);
        
        barChart.setTitle("Assigned time per task overview");
        barChart.setLegendVisible(true);
        
        
        barChart.getData().addAll(initializeTimeUsed(), intitalizeTimeAllotted());
        
        chartPane.setCenter(barChart);
    }
    
    private LineChart buildLineChart(Task t){
        xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        
        yAxis = new NumberAxis(am.getCurrentTask().getTimeUsed().get(0).getHours(),48,8);
        yAxis.setLabel("Hours Spent");
        
        LineChart  lc = new LineChart(xAxis, yAxis);
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Hours used on task");
        
        
            for (TaskTime tt : currentTask.getTimeUsed()) {
                series.getData().add(new XYChart.Data(tt.getDate().toString(), am.getLineChartData(t)));
            }
        
        lc.getData().add(series);
        return lc;
    }
    
    private void buildChart(){
        
        double totalTime = 0;
        PieChart pieChart = new PieChart();
        
        ObservableList<Task> allTasks = am.getCurrentProject().getTasks();
    
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Task task : allTasks)
        {
            pieChartData.add(new PieChart.Data(task.getName(), task.getHoursUsed()));
            System.out.println(task.getHoursUsed());
            totalTime += task.getHoursUsed();
        }
        
        for (PieChart.Data data : pieChartData)
        {
            data.setName(data.getName() + " (" + DF2.format(data.getPieValue() / totalTime * 100) + "%)");
        }
        
        pieChart.setData(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(180);
        pieChart.setLabelsVisible(true);
        pieChart.setTitle(am.getCurrentProject() + "");
    }
    
    private XYChart.Series initializeTimeUsed(){
        
        XYChart.Series timeUsedSeries = new XYChart.Series<>();
        for (Task task : am.getCurrentProject().getTasks()) {
            timeUsedSeries.getData().add(new XYChart.Data<>(task.getName(), task.getHoursUsed()));
        }
        timeUsedSeries.setName("Hours Used");
        return timeUsedSeries;
    }
    
    private XYChart.Series intitalizeTimeAllotted(){
        XYChart.Series timeAllottedSeries = new XYChart.Series<>();
        for (Task task : am.getCurrentProject().getTasks()) {
            timeAllottedSeries.getData().add(new XYChart.Data<>(task.getName(), task.getTimeAssigned()));
        }
        timeAllottedSeries.setName("Time Allotted");
        
        return timeAllottedSeries;
    }
}
