package med.easy.meditateeasy.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.InstructionRepository;
import med.easy.meditateeasy.model.Instruction;

import java.util.Objects;

public class InstructionPresenter {
    private final InstructionView view;
    private final InstructionRepository instructionRepository;
    private final ObservableList<Instruction> instructionList = FXCollections.observableArrayList();

    private InstructionPresenter(InstructionView view) {
        this.view = view;
        this.instructionRepository = new InstructionRepository();
        attachEvents();
        init();
        bindViewToModel();
    }

    private void bindViewToModel() {
        view.getInstructionList().setItems(instructionList);
    }

    private void init() {
        // load instructions from db
        reloadInstructionList();
    }

    private void reloadInstructionList() {
        instructionList.clear();
        instructionList.addAll(instructionRepository.getAllInstructions());
    }

    private void attachEvents() {
        // open video page
        view.getVideoBtn().setOnAction(event -> {
            VideoPresenter.show(view.getStage());
        });

        // open instruction page
        view.getInstructionBtn().setOnAction(event -> {
            InstructionPresenter.show(view.getStage());
        });

        // open start page
        view.getHomeBtn().setOnAction(event -> {
            StartPresenter.show(view.getStage());
        });
    }

    public static void show(Stage stage) {
        InstructionView view = new InstructionView();
        InstructionPresenter controller = new InstructionPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(InstructionPresenter.class.getResource("/instructionPage.css")).toExternalForm());
        stage.setTitle("Meditate Easy");
        stage.setScene(scene);
        stage.show();
    }
}
