/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
        
        
        
        ObservableList<Task> allTasks = am.getCurrentProject().getTasks();
    
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Task task : allTasks)
        {
            pieChartData.add(new PieChart.Data(task.getName(), task.getHoursUsed()));
            System.out.println(task.getHoursUsed());
        }
        
        pieChart.setData(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(180);
        pieChart.setLabelsVisible(true);
        pieChart.setTitle(am.getCurrentProject() + "");
    }

    @FXML
    private void handleNavigateBack(ActionEvent event)
    {
        Stage stage = (Stage) btnNavigateBack.getScene().getWindow();
        stage.close();
    }

}
