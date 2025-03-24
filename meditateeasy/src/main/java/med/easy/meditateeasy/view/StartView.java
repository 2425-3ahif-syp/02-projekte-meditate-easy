package med.easy.meditateeasy.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartView {
    private final VBox root = new VBox();
    
    // Navigation bar
    private final HBox navBar = new HBox();
    private final Button videoBtn = new Button("Anleitungsvideos");
    private final Button instructionBtn = new Button("Instruktionen");

    // ...

    public StartView() {
        init();
    }
    
    private void init() {
        // Root
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        // Navigation Bar
        navBar.getChildren().addAll(videoBtn, instructionBtn);
        navBar.getStyleClass().add("navbar");

        // Video button
        videoBtn.getStyleClass().add("video-btn");

        // Instruction button
        instructionBtn.getStyleClass().add("instruction-btn");

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

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
