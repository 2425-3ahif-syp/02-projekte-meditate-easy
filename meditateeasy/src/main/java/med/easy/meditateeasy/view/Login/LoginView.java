package med.easy.meditateeasy.view.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import med.easy.meditateeasy.MeditateEasyApp;

public class LoginView {

    private final VBox root = new VBox();
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Login");
    private final Button backButton = new Button("← Zurück");
    private final Label messageLabel = new Label();
    private final Label titleLabel = new Label("Anmeldung");

    public LoginView() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());
        root.setSpacing(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("login-root");

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

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(0, 0, 200, 0));
        topBar.setLeft(backButton);

        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 30, 0));
        VBox loginBox = new VBox(10, usernameField, passwordField, loginButton, messageLabel);
        loginBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                topBar,
                titleBox,
                loginBox
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

    public Button getBackButton() {
        return backButton;
    }
}
