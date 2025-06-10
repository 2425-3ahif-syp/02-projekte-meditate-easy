package med.easy.meditateeasy.view.admin.dashboard;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DifficultyRepository;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.database.VideoRepository;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.model.Video;
import med.easy.meditateeasy.util.Toast;
import med.easy.meditateeasy.view.admin.dashboard.Dialogs.DifficultyDialog;
import med.easy.meditateeasy.view.admin.dashboard.Dialogs.InstructionDialog;
import med.easy.meditateeasy.view.admin.dashboard.Dialogs.VideoDialog;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.Objects;


public class AdminDashboardPresenter {
    private final AdminDashboardView view;
    private final DifficultyRepository difficultyRepo = new DifficultyRepository();
    private final InstructionRepository instructionRepo = new InstructionRepository();
    private final VideoRepository videoRepo = new VideoRepository();

    public AdminDashboardPresenter(AdminDashboardView view) {
        this.view = view;
        setupDifficultyTab();
        setupInstructionTab();
        setupVideoTab();
        attachEvents();
    }

    private void attachEvents() {
        view.getBackButton().setOnAction(e -> {
            StartPresenter.show(view.getStage());
        });
    }

    private void setupDifficultyTab() {
        TableView<Difficulty> table = new TableView<>();

        TableColumn<Difficulty, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("difficultyId"));

        TableColumn<Difficulty, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        addTooltipToColumn(descCol);

        table.getColumns().addAll(idCol, descCol);

        table.getItems().addAll(difficultyRepo.getAllDifficulties());
        Button addButton = new Button("Neu");
        Button editButton = new Button("Bearbeiten");
        Button deleteButton = new Button("Löschen");

        addButton.getStyleClass().add("button-add");
        editButton.getStyleClass().add("button-edit");
        deleteButton.getStyleClass().add("button-delete");


        HBox buttonBox = new HBox(10, addButton, editButton, deleteButton);
        buttonBox.setPadding(new Insets(10));

        VBox container = new VBox(buttonBox, table);
        VBox.setVgrow(table, Priority.ALWAYS);
        container.setPadding(new Insets(10));
        view.difficultiesTab.setContent(container);
        addButton.setOnAction(e -> {
            DifficultyDialog dialog = new DifficultyDialog(null);
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Difficulty newDifficulty = dialog.getResult();
                if (difficultyRepo.addDifficulty(newDifficulty)) {
                    reloadDifficulties(table);
                    Toast.show(view.getStage(), "Difficulty erfolgreich hinzugefügt.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });

        editButton.setOnAction(e -> {
            Difficulty selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Toast.show(view.getStage(), "Bitte eine Schwierigkeit auswählen.", Toast.ToastType.WARNING, 1000);
                return;
            }
            DifficultyDialog dialog = new DifficultyDialog(selected);
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Difficulty updatedDifficulty = dialog.getResult();
                if (difficultyRepo.updateDifficulty(updatedDifficulty)) {
                    reloadDifficulties(table);
                    Toast.show(view.getStage(), "Difficulty erfolgreich bearbeitet.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });

        deleteButton.setOnAction(e -> {
            Difficulty selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                        "Möchtest du die Schwierigkeit '" + selected.getDescription() + "' wirklich löschen?",
                        ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        boolean success = difficultyRepo.deleteDifficulty(selected.getDifficultyId());
                        if (success) {
                            setupInstructionTab();
                            setupVideoTab();
                            Toast.show(view.getStage(), "Difficulty erfolgreich gelöscht.", Toast.ToastType.SUCCESS, 1000);
                            table.getItems().remove(selected);
                        } else {
                            Toast.show(view.getStage(), "Löschen fehlgeschlagen.", Toast.ToastType.ERROR, 1000);
                        }
                    }
                });
            } else {
                Toast.show(view.getStage(), "Bitte zuerst eine Schwierigkeit auswählen", Toast.ToastType.WARNING, 1000);
            }
        });
    }

    private void setupInstructionTab() {
        TableView<Instruction> table = new TableView<>();

        TableColumn<Instruction, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("instructionId"));

        TableColumn<Instruction, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        addTooltipToColumn(titleCol);

        TableColumn<Instruction, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        addTooltipToColumn(descCol);

        TableColumn<Instruction, Integer> difficultyCol = new TableColumn<>("Difficulty ID");
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficultyId"));

        table.getColumns().addAll(idCol, titleCol, descCol, difficultyCol);
        table.getItems().addAll(instructionRepo.getAllInstructions());

        Button addButton = new Button("Neu");
        Button editButton = new Button("Bearbeiten");
        Button deleteButton = new Button("Löschen");

        addButton.getStyleClass().add("button-add");
        editButton.getStyleClass().add("button-edit");
        deleteButton.getStyleClass().add("button-delete");

        HBox buttonBox = new HBox(10, addButton, editButton, deleteButton);
        buttonBox.setPadding(new Insets(10));

        VBox container = new VBox(buttonBox, table);
        VBox.setVgrow(table, Priority.ALWAYS);

        container.setPadding(new Insets(10));
        view.instructionsTab.setContent(container);

        addButton.setOnAction(e -> {
            InstructionDialog dialog = new InstructionDialog(null, difficultyRepo.getAllDifficulties());
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Instruction newInstruction = dialog.getResult();
                if (instructionRepo.addInstruction(newInstruction)) {
                    reloadInstructions(table);
                    Toast.show(view.getStage(), "Instruction erfolgreich hinzugefügt.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });

        editButton.setOnAction(e -> {
            Instruction selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Toast.show(view.getStage(), "Bitte eine Instruction auswählen.", Toast.ToastType.WARNING, 1000);
                return;
            }
            InstructionDialog dialog = new InstructionDialog(selected, difficultyRepo.getAllDifficulties());
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Instruction updatedInstruction = dialog.getResult();
                if (instructionRepo.updateInstruction(updatedInstruction)) {
                    reloadInstructions(table);
                    Toast.show(view.getStage(), "Instruction erfolgreich bearbeitet.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });
        deleteButton.setOnAction(e -> {
            Instruction selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                        "Möchtest du die Instruction '" + selected.getTitle() + "' wirklich löschen?",
                        ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        boolean success = instructionRepo.deleteInstruction(selected.getInstructionId());
                        if (success) {
                            Toast.show(view.getStage(), "Instruction erfolgreich gelöscht.", Toast.ToastType.SUCCESS, 1000);
                            table.getItems().remove(selected);
                        } else {
                            Toast.show(view.getStage(), "Löschen fehlgeschlagen.", Toast.ToastType.ERROR, 1000);
                        }
                    }
                });
            } else {
                Toast.show(view.getStage(), "Bitte zuerst eine Instruction auswählen.", Toast.ToastType.WARNING, 1000);
            }
        });
    }

    private void setupVideoTab() {
        TableView<Video> table = new TableView<>();

        TableColumn<Video, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("videoId"));

        TableColumn<Video, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        addTooltipToColumn(titleCol);

        TableColumn<Video, String> linkCol = new TableColumn<>("Link");
        linkCol.setCellValueFactory(new PropertyValueFactory<>("link"));
        addTooltipToColumn(linkCol);

        TableColumn<Video, Integer> difficultyCol = new TableColumn<>("Difficulty ID");
        difficultyCol.setCellValueFactory(new PropertyValueFactory<>("difficultyId"));

        table.getColumns().addAll(idCol, titleCol, linkCol, difficultyCol);
        table.getItems().addAll(videoRepo.getAllVideos());

        Button addButton = new Button("Neu");
        Button editButton = new Button("Bearbeiten");
        Button deleteButton = new Button("Löschen");

        addButton.getStyleClass().add("button-add");
        editButton.getStyleClass().add("button-edit");
        deleteButton.getStyleClass().add("button-delete");

        HBox buttonBox = new HBox(10, addButton, editButton, deleteButton);
        buttonBox.setPadding(new Insets(10));

        VBox container = new VBox(buttonBox, table);
        VBox.setVgrow(table, Priority.ALWAYS);

        container.setPadding(new Insets(10));
        view.videosTab.setContent(container);
        addButton.setOnAction(e -> {
            VideoDialog dialog = new VideoDialog(null, difficultyRepo.getAllDifficulties());
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Video newVideo = dialog.getResult();
                if (videoRepo.addVideo(newVideo)) {
                    reloadVideos(table);
                    Toast.show(view.getStage(), "Video erfolgreich hinzugefügt.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });

        editButton.setOnAction(e -> {
            Video selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Toast.show(view.getStage(), "Bitte ein Video auswählen.", Toast.ToastType.WARNING, 1000);
                return;
            }
            VideoDialog dialog = new VideoDialog(selected, difficultyRepo.getAllDifficulties());
            dialog.showAndWait();
            if (dialog.isSaved()) {
                Video updatedVideo = dialog.getResult();
                if (videoRepo.updateVideo(updatedVideo)) {
                    reloadVideos(table);
                    Toast.show(view.getStage(), "Video erfolgreich bearbeitet.", Toast.ToastType.SUCCESS, 1000);
                }
            }
        });

        deleteButton.setOnAction(e -> {
            Video selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                        "Möchtest du das Video '" + selected.getTitle() + "' wirklich löschen?",
                        ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        boolean success = videoRepo.deleteVideo(selected.getVideoId());
                        if (success) {
                            Toast.show(view.getStage(), "Video erfolgreich gelöscht.", Toast.ToastType.SUCCESS, 1000);
                            table.getItems().remove(selected);
                        } else {
                            Toast.show(view.getStage(), "Löschen fehlgeschlagen.", Toast.ToastType.ERROR, 1000);
                        }
                    }
                });
            } else {
                Toast.show(view.getStage(), "Bitte zuerst ein Video auswählen.", Toast.ToastType.WARNING, 1000);
            }
        });
    }

    public AdminDashboardView getView() {
        return view;
    }

    private <T> void addTooltipToColumn(TableColumn<T, String> column) {
        column.setCellFactory(col -> {
            TableCell<T, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setTooltip(null);
                    } else {
                        if (item.length() > 50) {
                            String displayText = item.substring(0, 50) + "...";
                            setText(displayText);

                            Tooltip tooltip = new Tooltip(item);
                            tooltip.setMaxWidth(200);
                            tooltip.setWrapText(true);
                            setTooltip(tooltip);
                        } else {
                            setText(item);
                            setTooltip(null);
                        }
                    }
                }
            };
            return cell;
        });
    }


    public static void show(Stage stage) {
        AdminDashboardView view = new AdminDashboardView();
        new AdminDashboardPresenter(view);
        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                AdminDashboardPresenter.class.getResource("/data-tables.css")).toExternalForm());
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void reloadInstructions(TableView<Instruction> table) {
        table.getItems().clear();
        table.getItems().addAll(instructionRepo.getAllInstructions());
    }
    private void reloadDifficulties(TableView<Difficulty> table) {
        table.getItems().clear();
        table.getItems().addAll(difficultyRepo.getAllDifficulties());
    }
    private void reloadVideos(TableView<Video> table) {
        table.getItems().clear();
        table.getItems().addAll(videoRepo.getAllVideos());
    }

}
