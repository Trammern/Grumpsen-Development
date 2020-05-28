/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import timetrackingexam.be.TaskLog;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class LogsController implements Initializable
{
    
    private AppModel am;

    @FXML
    private TableView<TaskLog> tblCRUD;
    @FXML
    private TableColumn<TaskLog, Date> clm1date;
    @FXML
    private TableColumn<TaskLog, String> clm1action;
    @FXML
    private TableColumn<TaskLog, String> clm1byuser;
    @FXML
    private TableColumn<TaskLog, String> clm1name;
    
    @FXML
    private TableView<TaskLog> tblTimelogs;
    @FXML
    private TableColumn<TaskLog, LocalDateTime> clm2start;
    @FXML
    private TableColumn<TaskLog, LocalDateTime> clm2end;
    @FXML
    private TableColumn<TaskLog, Double> clm2stime;
    @FXML
    private TableColumn<TaskLog, String> clm2sby;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        
        taskLogTable();
        timeLogTable();
    }    
    
    
    public void taskLogTable()
    {
        clm1action.setCellValueFactory(new PropertyValueFactory<>("action"));
        clm1date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblCRUD.getColumns().clear();
        tblCRUD.getColumns().addAll(clm1action, clm1date);
        tblCRUD.setItems(am.getLogs());  
    }
    
    public void timeLogTable()
    {
        clm2start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        clm2end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        clm2stime.setCellValueFactory(new PropertyValueFactory<>("submittedTime"));
        tblTimelogs.getColumns().clear();
        tblTimelogs.getColumns().addAll(clm2start, clm2end, clm2stime);
        tblTimelogs.setItems(am.getTimeLogs());
        
    }
}
