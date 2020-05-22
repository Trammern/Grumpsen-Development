/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.io.ObjectStreamConstants;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import timetrackingexam.bll.utilities.StatisticsCalculator;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author tramm
 */
public class DiagramViewController implements Initializable
{

    private final AppModel am = AppModel.getInstance();
    private final StatisticsCalculator sc = new StatisticsCalculator();
    private static final DecimalFormat DF2 = new DecimalFormat("#.##");
    private List<TaskTime> timeData;
    
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
        timeData = new ArrayList<>();
        
        for (Task task : am.getTasks()) {
            timeData.add(task.getTotalTimeUsed());
        }
        
        buildLineChart();
    }    

    @FXML
    private void handleNavigateBack(ActionEvent event){
    }
    
    private void buildBarChart(){
    
        
    }
    
    private void buildLineChart(){
        
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        
        lineChart.getData().add(initializeTimeUsed());
        
        chartPane.setCenter(lineChart);
        
    }
    
    private void buildPieChart(){
    }
    
    private XYChart.Series initializeTimeUsed(){
        
        XYChart.Series series = new XYChart.Series();
        for (TaskTime taskTime : timeData){
            series.getData().add(new XYChart.Data(
                    taskTime.getDate().toString(),
                    taskTime.getHours()));
        }
        return series;
    }
    
    private XYChart.Series intitalizeTimeAllotted(){
        return null;
    }
    
    private XYChart.Series initializeTimeSpent(){
        return null;
    }
}
