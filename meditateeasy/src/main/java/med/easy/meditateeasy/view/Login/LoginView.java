package med.easy.meditateeasy.view.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginView {

    private final VBox root = new VBox();
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Login");
    private final Label messageLabel = new Label();
    private final Label titleLabel = new Label("Anmeldung");

    public LoginView() {
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);
        root.setSpacing(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("login-root");

        titleLabel.getStyleClass().add("login-title");

        usernameField.setPromptText("Benutzername");
        passwordField.setPromptText("Passwort");

        usernameField.setMaxWidth(300);
        passwordField.setMaxWidth(300);
        loginButton.setMaxWidth(300);

        loginButton.getStyleClass().add("login-btn");

        messageLabel.getStyleClass().add("login-message");
        messageLabel.setMinHeight(20);

        root.getChildren().addAll(
                titleLabel,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );
    }

    public VBox getRoot() {
        return root;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setMessage(String msg, String color) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: " + color + ";");
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }


}
