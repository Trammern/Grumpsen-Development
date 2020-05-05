/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.be.Task;

/**
 * FXML Controller class
 *
 * @author tramm
 */
public class AssigenedTimeController implements Initializable
{

    private AppModel am;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    @FXML
    private BorderPane diagramPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        buildChart();
        
        
    }    
    
    
    private void buildChart(){
        
        
        
        xAxis = new CategoryAxis();
        xAxis.setLabel("Tasks");
        
        yAxis = new NumberAxis(0, 40, 2);
        yAxis.setLabel("Time in Hours");
        
        BarChart barChart = new BarChart<String, Number>(xAxis, yAxis);
        
        barChart.setTitle("Assigned time per task overview");
        barChart.setLegendVisible(true);
        
        
        barChart.getData().addAll(initializeTimeUsed(), intitalizeTimeAllotted());
        
        diagramPane.setCenter(barChart);
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
