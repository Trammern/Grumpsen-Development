/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author tramm
 */
public class UsedTimePerTaskController implements Initializable
{

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList
                (
                        new PieChart.Data("Hours", 35),
                        new PieChart.Data("Hours Left", 35)
                        
                );
        
        pieChart.setData(pieChartData);
                    
                
    }    
    
    
    
    
}
