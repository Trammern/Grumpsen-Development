/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

/**
 *
 * @author fauxtistic
 */
public class TaskFactory {
    
    public Task create(int id, String name, Project project, User user) {
        Task task = new Task(id, name, project, user);
        project.getTasks().add(task);
        user.getTasks().add(task);
        return task;
    }
    
}
