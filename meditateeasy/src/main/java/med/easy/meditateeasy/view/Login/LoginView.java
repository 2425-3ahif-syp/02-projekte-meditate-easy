package med.easy.meditateeasy.view.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.MeditateEasyApp;

public class LoginView {

    private final BorderPane root = new BorderPane();  // BorderPane statt VBox
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Login");
    private final Button backButton = new Button("← Zurück");
    private final Label messageLabel = new Label();
    private final Label titleLabel = new Label("Anmeldung");

    public LoginView() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());
        root.getStyleClass().add("login-root");

        HBox topBar = new HBox(backButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.TOP_LEFT);

        backButton.getStyleClass().add("back-button");
        backButton.setMinWidth(80);

        titleLabel.getStyleClass().add("login-title");

        usernameField.setPromptText("Benutzername");
        passwordField.setPromptText("Passwort");

        usernameField.setMaxWidth(300);
        passwordField.setMaxWidth(300);
        loginButton.setMaxWidth(300);

        loginButton.getStyleClass().add("login-btn");

        messageLabel.getStyleClass().add("login-message");
        messageLabel.setMinHeight(20);

        VBox loginBox = new VBox(10, titleLabel, usernameField, passwordField, loginButton, messageLabel);
        loginBox.setAlignment(Pos.TOP_CENTER);

        root.setTop(topBar);
        root.setCenter(loginBox);
    }

    public BorderPane getRoot() {
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

    public Button getBackButton() {
        return backButton;
    }
}
