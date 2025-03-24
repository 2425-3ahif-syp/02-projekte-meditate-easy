package med.easy.meditateeasy.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InstructionPresenter {
    private final InstructionView view;

    private InstructionPresenter(InstructionView view) {
        this.view = view;
        attachEvents();
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
