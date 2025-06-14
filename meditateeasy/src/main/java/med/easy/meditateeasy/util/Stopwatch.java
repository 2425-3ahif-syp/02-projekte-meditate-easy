package med.easy.meditateeasy.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Stopwatch extends VBox {
    private Timeline timeline;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private boolean running = false;
    
    private final Label timeLabel = new Label("00:00:00");
    private final Button startPauseButton = new Button("Start");
    private final Button resetButton = new Button("Zurücksetzen");
    
    public Stopwatch() {

    }
}