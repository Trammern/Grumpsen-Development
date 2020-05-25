/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.client;

import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.dal.facade.IClientDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;

/**
 *
 * @author fauxtistic
 */
public class ClientManager implements IClientManager {
    
    private IClientDal clientDal;
    
    public ClientManager() {
        clientDal = new TimeTrackDalFacade();
    }

    @Override
    public ObservableList<Client> getAllClients() {
        return clientDal.getAllClients();
    }

    @Override
    public boolean createClient(Client client) {
        return clientDal.createClient(client);
    }

    @Override
    public boolean updateClient(Client client) {
        return clientDal.updateClient(client);
    }

    @Override
    public boolean deleteClient(Client client) {
        return clientDal.deleteClient(client);
    }

    @Override
    public ObservableList<Project> getAllClientProjects(Client client) {
        return clientDal.getAllClientProjects(client);
    }
    
    
    
}
