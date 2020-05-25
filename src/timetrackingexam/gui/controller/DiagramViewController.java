/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.ObjectStreamConstants;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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
    private StatisticsCalculator sc;
    private static final DecimalFormat DF2 = new DecimalFormat("#.##");
    private List<TaskTime> timeData;
    
    @FXML
    private BorderPane chartPane;
    @FXML
    private JFXButton btnNavigateBack;
    @FXML
    private JFXComboBox<String> cmbChooseChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        timeData = new ArrayList<>();
        sc = new StatisticsCalculator(am.getCurrentProject());
        
        for (Task task : am.getTasks()) {
            timeData.add(task.getTotalTimeUsed());
        }
        
        
        
        cmbChooseChart.getItems().add("Piechart");
        cmbChooseChart.getItems().add("Linechart");
        
        
        
    }    
    
    public void test()
    {
        System.out.println(cmbChooseChart.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleNavigateBack(ActionEvent event)
    {
        Stage stage = (Stage) btnNavigateBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handleCombo(ActionEvent event)
    {
        if("Piechart".equals(cmbChooseChart.getSelectionModel().getSelectedItem()))
        {
            buildPieChart();
        }
        if("Linechart".equals(cmbChooseChart.getSelectionModel().getSelectedItem()))
        {
            buildLineChart();
        }
    }
    
    private void buildLineChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        
        LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        
        lineChart.getData().add(sc.timeGrowth());
        lineChart.setTitle("Growth over time");
        lineChart.setLegendVisible(false);
        chartPane.setCenter(lineChart);
    }
    
    private void buildPieChart(){
        PieChart chart = new PieChart(sc.getHoursPerTaskUsed());
        chart.setTitle("Time used per task");
        chart.setLegendVisible(false);

        chartPane.setCenter(chart);
    }
}
