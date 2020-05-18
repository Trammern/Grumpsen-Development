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
    
    public static final int DEFAULT_MILLIS_SHOW_DELAY = 500;
    public static final int DEFAULT_MILLIS_HIDE_DELAY = 0;
    
    public static Tooltip create(String text) {
        return create(text, DEFAULT_MILLIS_SHOW_DELAY, DEFAULT_MILLIS_HIDE_DELAY);
    }
    
    public static Tooltip create(String text, int millisShowDelay, int millisHideDelay) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setShowDelay(Duration.millis(millisShowDelay));
        tooltip.setHideDelay(Duration.millis(millisHideDelay));
        return tooltip;
    }
    
}
