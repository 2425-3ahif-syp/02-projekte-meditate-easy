package med.easy.meditateeasy.view.Instruction;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import med.easy.meditateeasy.model.Instruction;

public class InstructionDetailView {
    private final VBox root = new VBox();
    private final HBox header = new HBox();
    private final Button backButton = new Button("← Zurück");
    private final Label titleLabel = new Label();
    private final Label difficultyLabel = new Label();
    private final Label descriptionLabel = new Label();

    public InstructionDetailView() {
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);

        header.getChildren().add(backButton);
        header.getStyleClass().add("detail-header");

        // Layout
        root.setSpacing(20);
        root.getStyleClass().add("detail-container");
        titleLabel.getStyleClass().add("detail-title");
        difficultyLabel.getStyleClass().add("detail-difficulty");
        descriptionLabel.getStyleClass().add("detail-description");

        root.getChildren().addAll(header, titleLabel, difficultyLabel, descriptionLabel);

    }

    public void setInstruction(Instruction instruction) {
        titleLabel.setText(instruction.getTitle());
        difficultyLabel.setText("Schwierigkeit: " + instruction.getDifficulty());
        descriptionLabel.setText(instruction.getDescription());
    }

    public VBox getRoot() { return root; }
    public Button getBackButton() { return backButton; }
}