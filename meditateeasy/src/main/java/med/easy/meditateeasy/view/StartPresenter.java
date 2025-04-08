package med.easy.meditateeasy.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class StartPresenter {
    private final StartView view;
    private static double maxX;
    private static double maxY;

    private StartPresenter(StartView view) {
        this.view = view;
        attachEvents();
    }

    private void attachEvents() {
        view.getVideoBtn().setOnAction(event -> {
            saveWindowPosition(view.getStage());
            VideoPresenter.show(view.getStage());
        });

        view.getInstructionBtn().setOnAction(event -> {
            saveWindowPosition(view.getStage());
            InstructionPresenter.show(view.getStage());
        });
    }

    private void saveWindowPosition(Stage stage) {
        maxX = stage.getX();
        maxY = stage.getY();
    }

    public static void show(Stage stage) {
        StartView view = new StartView();
        StartPresenter controller = new StartPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                StartPresenter.class.getResource("/startPage.css")).toExternalForm());

        stage.setTitle("Meditate Easy");
        stage.setScene(scene);

        stage.setOnShown(e -> {
            maxX = stage.getX();
            maxY = stage.getY();
        });

        stage.show();
    }

    public static double getMaxX() {
        return maxX;
    }

    public static double getMaxY() {
        return maxY;
    }
}