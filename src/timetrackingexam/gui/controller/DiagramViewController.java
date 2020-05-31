/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.ObjectStreamConstants;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
    private BarChart bc;
    
    @FXML
    private BorderPane chartPane;
    @FXML
    private JFXButton btnNavigateBack;
    @FXML
    private JFXComboBox<String> cmbChooseChart;
    @FXML
    private JFXDatePicker dateFrom;
    @FXML
    private JFXDatePicker dateTo;
    @FXML
    private JFXButton tbnInterval;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        sc = new StatisticsCalculator(am.getCurrentProject());
        
        cmbChooseChart.getItems().add("Piechart");
        cmbChooseChart.getItems().add("Linechart");
        buildBarChart();
        
        
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
            buildBarChart();
        }
    }
    
    private void buildBarChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        bc = new BarChart(xAxis, yAxis);
        
        bc.setTitle("hours used over Time");
        xAxis.setLabel("Date");
        yAxis.setLabel("Hours");
        
        
                
        chartPane.setCenter(bc);
    }
    
    private void changeBarChart(){
        
    }
    
    private void buildPieChart(){
        PieChart chart = new PieChart(sc.getHoursPerTaskUsed());
        
        chart.setTitle("Time used per task");
        chart.setLegendVisible(false);
        
        chartPane.setCenter(chart);
        
    }

    @FXML
    private void handleDay(ActionEvent event) {
        buildBarChart();
        bc.getData().add(sc.timeUsedPerDay());
        dateFrom.setVisible(true);
        dateTo.setVisible(true);
        tbnInterval.setVisible(true);
    }

    @FXML
    private void handleWeek(ActionEvent event) {
        buildBarChart();
        bc.getData().add(sc.timeUsedPerWeek());
        dateFrom.setVisible(false);
        dateTo.setVisible(false);
        tbnInterval.setVisible(false);
    }

    @FXML
    private void handleMonth(ActionEvent event) {
        buildBarChart();
        bc.getData().add(sc.timeUsedPerMonth());
        dateFrom.setVisible(false);
        dateTo.setVisible(false);
        tbnInterval.setVisible(false);
    }

    @FXML
    private void handleInterval(ActionEvent event) {
        buildBarChart();
        
        
        bc.getData().add(sc.intervalSeries(dateFrom.getValue(), dateTo.getValue()));
    }
}
