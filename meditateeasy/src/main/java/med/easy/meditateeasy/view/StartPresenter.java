package med.easy.meditateeasy.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class StartPresenter {

    private final StartView view;

    private StartPresenter(StartView view) {
        this.view = view;
    }

    public static void show(Stage stage) {
        StartView view = new StartView();
        StartPresenter controller = new StartPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(StartPresenter.class.getResource("/startPage.css")).toExternalForm());
        stage.setTitle("Meditate Easy");
        stage.setScene(scene);
        stage.show();
    }
}
