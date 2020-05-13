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
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import timetrackingexam.be.Task;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author tramm
 */
public class UsedTimePerTaskController implements Initializable
{

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private AppModel am;
    
    @FXML
    private PieChart pieChart;
    @FXML
    private JFXButton btnNavigateBack;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        buildChart();
    }

    @FXML
    private void handleNavigateBack(ActionEvent event)
    {
        Stage stage = (Stage) btnNavigateBack.getScene().getWindow();
        stage.close();
    }
    
    private void buildChart(){
        
        double totalTime = 0;
        
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
            data.setName(data.getName() + " (" + df2.format(data.getPieValue() / totalTime * 100) + "%)");
        }
        
        pieChart.setData(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(180);
        pieChart.setLabelsVisible(true);
        pieChart.setTitle(am.getCurrentProject() + "");
    }

}
