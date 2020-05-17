/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.util;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 *
 * @author fauxtistic
 */
public class TooltipFactory {
    
    public static Tooltip create(String text, int millisToShow, int millisToHide) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.millis(millisToShow));
        tooltip.setHideDelay(Duration.millis(millisToHide));
        return tooltip;
    }
    
}
