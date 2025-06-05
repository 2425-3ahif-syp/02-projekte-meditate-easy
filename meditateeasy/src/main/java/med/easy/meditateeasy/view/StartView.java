package med.easy.meditateeasy.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class StartView {
    private final VBox root = new VBox();

    // Navigation bar
    private final HBox navBar = new HBox();
    private final Button videoBtn = new Button("Anleitungsvideos");
    private final Button instructionBtn = new Button("Instruktionen");
    private final Button homeBtn = new Button("Home");
    private final Button loginBtn = new Button("Login");
    Region spacer = new Region();


    // Welcome text and image
    private final HBox welcomeContainer = new HBox();
    private final Label welcomeText = new Label("Welcome to Meditate Easy!");
    private final ImageView welcomeImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/welcome-image.png"))));

    public StartView() {
        init();
    }

    private void init() {
        // Root
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Navigation Bar
        navBar.getChildren().addAll(homeBtn, videoBtn, instructionBtn, spacer, loginBtn);
        navBar.getStyleClass().add("navbar");

        // Video button
        videoBtn.getStyleClass().add("video-btn");

        // Instruction button
        instructionBtn.getStyleClass().add("instruction-btn");

        // Home button
        homeBtn.getStyleClass().add("home-btn");

        // Welcome container
        welcomeContainer.getChildren().addAll(welcomeText, welcomeImage);
        welcomeContainer.setAlignment(Pos.CENTER);
        welcomeContainer.setSpacing(40);
        welcomeContainer.getStyleClass().add("welcome-container");

        // Welcome text
        welcomeText.getStyleClass().add("welcome-text");

        // Welcome image
        welcomeImage.setFitWidth(400);
        welcomeImage.setPreserveRatio(true);
        welcomeImage.getStyleClass().add("welcome-image");

        // Generate root view
        root.getChildren().addAll(navBar, welcomeContainer);
    }

    public VBox getRoot() {
        return root;
    }

    public Button getVideoBtn() {
        return videoBtn;
    }

    public Button getInstructionBtn() {
        return instructionBtn;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }

    public Button getHomeBtn() {
        return homeBtn;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }
}
