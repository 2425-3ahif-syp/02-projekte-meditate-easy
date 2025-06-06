package med.easy.meditateeasy.view.Instruction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DifficultyRepository;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Instruction;
import med.easy.meditateeasy.view.Login.LoginPresenter;
import med.easy.meditateeasy.view.StartPresenter;
import med.easy.meditateeasy.view.Video.VideoPresenter;

import java.util.Objects;
import java.util.function.Predicate;

public class InstructionPresenter {
    private final InstructionView view;
    private final InstructionRepository instructionRepository;
    private final ObservableList<Instruction> instructionList = FXCollections.observableArrayList();
    private final FilteredList<Instruction> filteredInstructions;
    private final DifficultyRepository difficultyRepository = new DifficultyRepository();

    public InstructionPresenter(InstructionView view) {
        this.view = view;
        this.instructionRepository = new InstructionRepository();
        this.filteredInstructions = new FilteredList<>(instructionList);
        loadDifficulties();
        attachEvents();
        init();
        bindViewToModel();
    }

    private void bindViewToModel() {
        view.getListView().setItems(filteredInstructions);

        view.getSearchField().textProperty().addListener((obs, old, newVal) -> updateFilter());
        view.getDifficultyFilter().getSelectionModel().selectedItemProperty()
                .addListener((obs, old, newVal) -> updateFilter());
    }

    private void updateFilter() {
        String searchText = view.getSearchField().getText().toLowerCase();
        String difficulty = view.getDifficultyFilter().getValue();

        Predicate<Instruction> predicate = instruction ->
                instruction.getTitle().toLowerCase().contains(searchText) &&
                        (difficulty.equals("Alle") || instruction.getDifficulty().toString().equals(difficulty));

        filteredInstructions.setPredicate(predicate);
    }

    private void init() {
        reloadInstructionList();
        view.getListView().getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            if (newVal != null) {
                InstructionDetailPresenter.show(view.getStage(), newVal);
            }
        });
    }

    private void reloadInstructionList() {
        instructionList.clear();
        instructionList.addAll(instructionRepository.getAllInstructions());
    }

    private void attachEvents() {
        view.getNavBar().getVideoBtn().setOnAction(e -> VideoPresenter.show(view.getStage()));
        view.getNavBar().getInstructionBtn().setOnAction(e -> InstructionPresenter.show(view.getStage()));
        view.getNavBar().getHomeBtn().setOnAction(event -> {
            StartPresenter.show(view.getStage());
        });
        view.getNavBar().getLoginBtn().setOnAction(e -> {
            LoginPresenter.show(view.getStage());
        });
    }

    private void loadDifficulties() {
        view.getDifficultyFilter().getItems().clear();
        view.getDifficultyFilter().getItems().add("Alle");

        for (Difficulty diff : difficultyRepository.getAllDifficulties()) {
            view.getDifficultyFilter().getItems().add(diff.getDescription());
        }

        view.getDifficultyFilter().setValue("Alle");
    }


    public static void show(Stage stage) {
        InstructionView view = new InstructionView();
        new InstructionPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                InstructionPresenter.class.getResource("/instructionPage.css")).toExternalForm());
        stage.setTitle("Meditate Easy - Anleitungen");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}