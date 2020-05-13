/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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
public class TimeGrowthController implements Initializable
{

    AppModel am = AppModel.getInstance();
    private Task currentTask;
    
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
        chartPane.setCenter(buildChart(currentTask));
    }    

    @FXML
    private void handleNavigateBack(ActionEvent event)
    {
        Stage stage = (Stage) btnNavigateBack.getScene().getWindow();
        stage.close();
    }
    
    private LineChart buildChart(Task t){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        
        NumberAxis yAxis = new NumberAxis(am.getCurrentTask().getTimeUsed().get(0).getHours(),48,8);
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
    
}
