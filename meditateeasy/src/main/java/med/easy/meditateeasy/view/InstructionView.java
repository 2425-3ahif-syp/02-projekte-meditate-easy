package med.easy.meditateeasy.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InstructionView {
    private final VBox root = new VBox();

    // Navigation bar
    private final HBox navBar = new HBox();
    private final Button videoBtn = new Button("Anleitungsvideos");
    private final Button instructionBtn = new Button("Instruktionen");
    private final Button homeBtn = new Button("Home");

    public InstructionView() {
        init();
    }

    private void init() {
        // Root
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        // Navigation Bar
        navBar.getChildren().addAll(videoBtn, instructionBtn, homeBtn);
        navBar.getStyleClass().add("navbar");

        // Video button
        videoBtn.getStyleClass().add("video-btn");

        // Instruction button
        instructionBtn.getStyleClass().add("instruction-btn");

        // Home button
        homeBtn.getStyleClass().add("home-btn");

        // Generate root view
        root.getChildren().addAll(navBar);
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

    public Button getHomeBtn() {
        return homeBtn;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
