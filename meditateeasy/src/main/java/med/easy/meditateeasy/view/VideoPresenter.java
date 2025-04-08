package med.easy.meditateeasy.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class VideoPresenter {
    private final VideoView view;

    private VideoPresenter(VideoView view) {
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


    }

    public static void show(Stage stage) {
        VideoView view = new VideoView();
        VideoPresenter controller = new VideoPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(VideoPresenter.class.getResource("/videoPage.css")).toExternalForm());
        stage.setTitle("Meditate Easy");
        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }
}
