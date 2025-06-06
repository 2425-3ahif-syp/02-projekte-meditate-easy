package med.easy.meditateeasy.util;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.Objects;

public class Toast {

    public enum ToastType {
        SUCCESS, ERROR, INFO, WARNING
    }

    public static void show(Stage ownerStage, String message, ToastType type, int durationMillis) {
        Popup popup = new Popup();

        Label iconLabel = new Label(getIconUnicode(type));
        iconLabel.setFont(new Font(18));
        iconLabel.getStyleClass().addAll("toast-icon", "toast-" + type.name().toLowerCase());

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().addAll("toast-label", "toast-" + type.name().toLowerCase());

        HBox content = new HBox(10, iconLabel, messageLabel);
        content.setAlignment(Pos.CENTER_LEFT);
        content.getStyleClass().addAll("toast-box", "toast-" + type.name().toLowerCase());

        StackPane pane = new StackPane(content);
        pane.setOpacity(0);

        pane.getStylesheets().add(Objects.requireNonNull(
                StartPresenter.class.getResource("/toast.css")).toExternalForm());


        popup.getContent().add(pane);
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);

        popup.show(ownerStage,
                ownerStage.getX() + ownerStage.getWidth() / 2 - 150,
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

    private static String getIconUnicode(ToastType type) {
        return switch (type) {
            case SUCCESS -> "✔";
            case ERROR -> "✖";
            case INFO -> "ℹ";
            case WARNING -> "⚠";
        };
    }
}
