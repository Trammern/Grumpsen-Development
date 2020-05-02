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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
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
    
    @FXML
    private BorderPane chartPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        
        NumberAxis yAxis = new NumberAxis(0,1500,150);
        yAxis.setLabel("Hours Spent");
        
        LineChart  lc = new LineChart(xAxis, yAxis);
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Hours used on task");
        
        
            for (TaskTime tt : am.getCurrentTask().getTimeUsed()) {
                series.getData().add(new XYChart.Data(tt.getDateOfWeek(), am.getCurrentTask().getTotalTimeUsed().getTotalMinutes()));
                System.out.println("got data point");
            }
        
    lc.getData().add(series);
    chartPane.setCenter(lc);
    }    
    
}
