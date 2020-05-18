/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.util;

import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;

/**
 *
 * @author fauxtistic
 */
public class NodeCustomizer {
    
    public static void nodeEffect(Node node) {
        node.setOnMouseEntered(e -> {
            DropShadow ds = new DropShadow();
            ds.setInput(new Bloom(0.7));
            node.setEffect(ds);
            //node.setEffect(new DropShadow());
            //node.setEffect(new Bloom(0.65));
        });
        node.setOnMouseExited(e -> {
           node.setEffect(null); 
        });
    }
    
}
