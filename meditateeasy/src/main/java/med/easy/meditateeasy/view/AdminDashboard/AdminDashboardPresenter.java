package med.easy.meditateeasy.view.AdminDashboard;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DifficultyRepository;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.database.VideoRepository;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.model.Video;


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

        TableColumn<Instruction, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

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

        TableColumn<Video, String> linkCol = new TableColumn<>("Link");
        linkCol.setCellValueFactory(new PropertyValueFactory<>("link"));

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
}
