package med.easy.meditateeasy.view.AdminDashboard.Dialogs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.util.Toast;

import java.util.Objects;

public class DifficultyDialog extends Stage {

    private final TextField descriptionField = new TextField();

    private Difficulty difficulty;
    private boolean saved = false;

    public DifficultyDialog(Difficulty difficulty) {
        this.difficulty = difficulty;

        setTitle(difficulty == null ? "Neue Schwierigkeit hinzufügen" : "Schwierigkeit bearbeiten");
        initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getStyleClass().add("root-dialog");

        descriptionField.setPromptText("Beschreibung");
        descriptionField.getStyleClass().add("input-field");

        if (difficulty != null) {
            descriptionField.setText(difficulty.getDescription());
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
                new Label("Beschreibung:"), descriptionField,
                buttons
        );

        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/dialog.css")).toExternalForm());
        setScene(new Scene(root, 300, 185));
    }

    private boolean validateInput() {
        if (descriptionField.getText().trim().isEmpty()) {
            Toast.show(this, "Beschreibung darf nicht leer sein.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }

    public Difficulty getResult() {
        if (!saved) return null;

        if (difficulty == null) {
            difficulty = new Difficulty(0, descriptionField.getText().trim());
        } else {
            difficulty.setDescription(descriptionField.getText().trim());
        }
        return difficulty;
    }
}
