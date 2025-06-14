package med.easy.meditateeasy.view.instruction;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.util.Stopwatch;

public class InstructionDetailView {
    private final VBox root = new VBox();
    private final HBox header = new HBox();
    private final Button backButton = new Button("← Zurück");
    private final Label titleLabel = new Label();
    private final Label difficultyLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Stopwatch stopwatch = new Stopwatch();
    private final HBox contentContainer = new HBox(20);
    private final VBox instructionContainer = new VBox(20);

    public InstructionDetailView() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());

        header.getChildren().add(backButton);
        header.getStyleClass().add("detail-header");

        // Layout
        root.setSpacing(20);
        root.getStyleClass().add("detail-container");
        titleLabel.getStyleClass().add("detail-title");
        difficultyLabel.getStyleClass().add("detail-difficulty");
        descriptionLabel.getStyleClass().add("detail-description");

        instructionContainer.getChildren().addAll(titleLabel, difficultyLabel, descriptionLabel);

        stopwatch.setAlignment(Pos.TOP_CENTER);
        
        contentContainer.getChildren().addAll(instructionContainer, stopwatch);
        root.getChildren().addAll(header, contentContainer);

        root.getStylesheets().add(getClass().getResource("/stopwatch.css").toExternalForm());
    }

    public void setInstruction(Instruction instruction) {
        titleLabel.setText(instruction.getTitle());
        difficultyLabel.setText("Schwierigkeit: " + instruction.getDifficulty());
        descriptionLabel.setText(instruction.getDescription());
    }

    public VBox getRoot() { return root; }
    public Button getBackButton() { return backButton; }
}