package med.easy.meditateeasy.view.admin.dashboard.Dialogs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Video;
import med.easy.meditateeasy.util.Toast;

import java.util.List;
import java.util.Objects;

public class VideoDialog extends Stage {

    private final TextField titleField = new TextField();
    private final TextField urlField = new TextField();
    private final ComboBox<Difficulty> difficultyComboBox = new ComboBox<>();

    private Video video;
    private boolean saved = false;

    public VideoDialog(Video video, List<Difficulty> difficulties) {
        this.video = video;

        setTitle(video == null ? "Neues Video" : "Video bearbeiten");
        initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getStyleClass().add("root-dialog");

        titleField.setPromptText("Titel");
        urlField.setPromptText("URL");
        titleField.getStyleClass().add("input-field");
        urlField.getStyleClass().add("input-field");
        difficultyComboBox.getStyleClass().add("combo-box");

        difficultyComboBox.getItems().addAll(difficulties);

        if (video != null) {
            titleField.setText(video.getTitle());
            urlField.setText(video.getLink());
            difficultyComboBox.getSelectionModel().select(
                    difficulties.stream()
                            .filter(d -> d.getDifficultyId() == video.getDifficultyId())
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
                new Label("URL:"), urlField,
                new Label("Schwierigkeitsgrad:"), difficultyComboBox,
                buttons
        );
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/dialog.css")).toExternalForm());
        setScene(new Scene(root, 600, 350));
    }

    private boolean validateInput() {
        if (titleField.getText().trim().isEmpty()) {
            Toast.show(this, "Titel darf nicht leer sein.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        if (!urlField.getText().matches("^https://www\\.youtube\\.com/embed/.+")) {
            Toast.show(this, "Der Link muss im Format https://www.youtube.com/embed/{Text} sein.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        if (difficultyComboBox.getSelectionModel().isEmpty()) {
            Toast.show(this, "Bitte Schwierigkeitsgrad auswählen.", Toast.ToastType.WARNING, 1000, true);
            return false;
        }
        return true;
    }

    public boolean isSaved() {
        return saved;
    }

    public Video getResult() {
        if (!saved) return null;
        if (video == null) {
            video = new Video(0, titleField.getText().trim(), urlField.getText().trim(), 0);
        }
        video.setTitle(titleField.getText().trim());
        video.setLink(urlField.getText().trim());
        Difficulty selected = difficultyComboBox.getSelectionModel().getSelectedItem();
        video.setDifficultyId(selected.getDifficultyId());
        return video;
    }
}
