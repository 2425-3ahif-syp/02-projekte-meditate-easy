package med.easy.meditateeasy.view.admin.dashboard.Dialogs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.util.Toast;

import java.util.List;
import java.util.Objects;

public class InstructionDialog extends Stage {

    private final TextField titleField = new TextField();
    private final TextArea descriptionArea = new TextArea();
    private final ComboBox<Difficulty> difficultyComboBox = new ComboBox<>();

    private Instruction instruction;
    private boolean saved = false;

    public InstructionDialog(Instruction instruction, List<Difficulty> difficulties) {
        this.instruction = instruction;

        setTitle(instruction == null ? "Neue Instruction" : "Instruction bearbeiten");
        initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getStyleClass().add("root-dialog");

        titleField.setPromptText("Titel");
        descriptionArea.setPromptText("Beschreibung");
        descriptionArea.setWrapText(true);

        titleField.getStyleClass().add("input-field");
        descriptionArea.getStyleClass().add("text-area");
        difficultyComboBox.getStyleClass().add("combo-box");

        difficultyComboBox.getItems().addAll(difficulties);

        if (instruction != null) {
            titleField.setText(instruction.getTitle());
            descriptionArea.setText(instruction.getDescription());
            difficultyComboBox.getSelectionModel().select(
                    difficulties.stream()
                            .filter(d -> d.getDifficultyId() == instruction.getDifficultyId())
                            .findFirst()
                            .orElse(null)
            );
        }

        Button saveButton = new Button("Speichern");
        saveButton.setDefaultButton(true);
        saveButton.getStyleClass().add("button-save");

        saveButton.setOnAction(e -> {
            if (validateInput()) {
                saved = true;
                close();
            }
        });

        Button cancelButton = new Button("Abbrechen");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> close());
        cancelButton.getStyleClass().add("button-cancel");

        HBox buttons = new HBox(10, saveButton, cancelButton);

        root.getChildren().addAll(
                new Label("Titel:"), titleField,
                new Label("Beschreibung:"), descriptionArea,
                new Label("Schwierigkeitsgrad:"), difficultyComboBox,
                buttons
        );

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/dialog.css")).toExternalForm());
        setScene(new Scene(root, 400, 525));
    }

    private boolean validateInput() {
        if (titleField.getText().trim().isEmpty()) {
            Toast.show(this, "Titel darf nicht leer sein.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        if (difficultyComboBox.getSelectionModel().isEmpty()) {
            Toast.show(this, "Bitte Schwierigkeitsgrad auswählen.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        if(descriptionArea.getText().trim().isEmpty()) {
            Toast.show(this, "Beschreibung darf nicht leer sein.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }

    public Instruction getResult() {
        if (!saved) return null;
        if (instruction == null) {
            instruction = new Instruction();
        }
        instruction.setTitle(titleField.getText().trim());
        instruction.setDescription(descriptionArea.getText().trim());
        Difficulty selected = difficultyComboBox.getSelectionModel().getSelectedItem();
        instruction.setDifficultyId(selected.getDifficultyId());
        return instruction;
    }
}
