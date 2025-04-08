package med.easy.meditateeasy.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.model.Instruction;

import java.util.Objects;
import java.util.function.Predicate;

public class InstructionPresenter {
    private final InstructionView view;
    private final InstructionRepository instructionRepository;
    private final ObservableList<Instruction> instructionList = FXCollections.observableArrayList();
    private final FilteredList<Instruction> filteredInstructions;

    public InstructionPresenter(InstructionView view) {
        this.view = view;
        this.instructionRepository = new InstructionRepository();
        this.filteredInstructions = new FilteredList<>(instructionList);
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
        view.getVideoBtn().setOnAction(e -> VideoPresenter.show(view.getStage()));
        view.getInstructionBtn().setOnAction(e -> InstructionPresenter.show(view.getStage()));
        view.getHomeBtn().setOnAction(event -> {
            StartPresenter.show(view.getStage());
        });
    }

    public static void show(Stage stage) {
        InstructionView view = new InstructionView();
        new InstructionPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                InstructionPresenter.class.getResource("/instructionPage.css")).toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}