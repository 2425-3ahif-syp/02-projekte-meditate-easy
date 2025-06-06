package med.easy.meditateeasy.util;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.Objects;

public class Toast {

    public static void show(Stage ownerStage, String message, int durationMillis) {
        Popup popup = new Popup();

        Label label = new Label(message);
        label.getStyleClass().add("toast-label");

        StackPane pane = new StackPane(label);
        pane.setOpacity(0);

        pane.getStylesheets().add(Objects.requireNonNull(
                StartPresenter.class.getResource("/toast.css")).toExternalForm());


        popup.getContent().add(pane);
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        popup.show(ownerStage,
                ownerStage.getX() + ownerStage.getWidth() / 2 - 100,
                ownerStage.getY() + ownerStage.getHeight() - 100);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), pane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setDelay(Duration.millis(durationMillis));
        fadeOut.setOnFinished(e -> popup.hide());

        fadeOut.play();
    }
}