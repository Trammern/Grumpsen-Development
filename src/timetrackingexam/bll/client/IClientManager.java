/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.client;

import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;

/**
 *
 * @author fauxtistic
 */
public interface IClientManager {
    
    public ObservableList<Client> getAllClients();
    public boolean createClient(Client client);
    public boolean updateClient(Client client);
    public boolean deleteClient(Client client);
    public ObservableList<Project> getAllClientProjects(Client client);
    
}
