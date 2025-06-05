package med.easy.meditateeasy.view.Instruction;

import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.model.Instruction;

public class InstructionDetailPresenter {
    private final InstructionDetailView view;
    private final Stage stage;
    private final Instruction instruction;

    public InstructionDetailPresenter(InstructionDetailView view, Stage stage, Instruction instruction) {
        this.view = view;
        this.stage = stage;
        this.instruction = instruction;
        setupView();
        attachEvents();
    }

    private void setupView() {
        view.setInstruction(instruction);
        Scene scene = new Scene(view.getRoot());
        String cssPath = getClass().getResource("/detailPage.css").toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void attachEvents() {
        view.getBackButton().setOnAction(e -> {
            InstructionPresenter.show(stage);
        });
    }

    public static void show(Stage stage, Instruction instruction) {
        InstructionDetailView view = new InstructionDetailView();
        new InstructionDetailPresenter(view, stage, instruction);
    }
}