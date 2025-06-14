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
        setupTimeline();
        setupUI();
    }

    private void setupTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
            updateTimeLabel();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void setupUI() {

        // Styling and layout
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("stopwatch-container");

        timeLabel.getStyleClass().add("stopwatch-time");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startPauseButton, resetButton);

        startPauseButton.getStyleClass().add("stopwatch-button");
        resetButton.getStyleClass().add("stopwatch-button");

        // Event handlers for buttons
        startPauseButton.setOnAction(event -> toggleStartPause());
        resetButton.setOnAction(event -> reset());

        this.getChildren().addAll(new Label("Stoppuhr"), timeLabel, buttonBox);
    }

    private void updateTimeLabel() {
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    private void toggleStartPause() {
        if (running) {
            timeline.pause();
            startPauseButton.setText("Fortsetzen");
            running = false;
        } else {
            timeline.play();
            startPauseButton.setText("Pause");
            running = true;
        }
    }

    private void reset() {
        timeline.stop();
        hours = 0;
        minutes = 0;
        seconds = 0;
        updateTimeLabel();
        startPauseButton.setText("Start");
        running = false;
    }
}