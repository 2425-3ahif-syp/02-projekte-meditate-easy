package med.easy.meditateeasy.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        // Instruction button

        // Generate root view
        root.getChildren().addAll(navBar);
    }

    public VBox getRoot() {
        return root;
    }
}
