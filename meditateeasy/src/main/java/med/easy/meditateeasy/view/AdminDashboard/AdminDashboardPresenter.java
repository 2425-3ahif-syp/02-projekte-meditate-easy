package med.easy.meditateeasy.view.AdminDashboard;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DifficultyRepository;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.database.VideoRepository;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.model.Video;
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
    }

    public static void show(Stage stage) {
        AdminDashboardView view = new AdminDashboardView();
        new AdminDashboardPresenter(view);
        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                AdminDashboardPresenter.class.getResource("/data-tables.css")).toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
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

        VBox container = new VBox(table);
        container.setPadding(new Insets(10));
        view.difficultiesTab.setContent(container);
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

        VBox container = new VBox(table);
        container.setPadding(new Insets(10));
        view.instructionsTab.setContent(container);
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

        VBox container = new VBox(table);
        container.setPadding(new Insets(10));
        view.videosTab.setContent(container);
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
}
