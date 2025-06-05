package med.easy.meditateeasy.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.view.Instruction.InstructionPresenter;
import med.easy.meditateeasy.view.Login.LoginPresenter;
import med.easy.meditateeasy.view.Video.VideoPresenter;

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
        view.getNavBar().getVideoBtn().setOnAction(event -> {
            VideoPresenter.show(view.getStage());
        });

        view.getNavBar().getInstructionBtn().setOnAction(event -> {
            InstructionPresenter.show(view.getStage());
        });

        view.getNavBar().getLoginBtn().setOnAction(e -> {
            LoginPresenter.show(view.getStage());
        });


        view.getNavBar().getHomeBtn().setOnAction(event -> StartPresenter.show(view.getStage()));
    }

    public static void show(Stage stage) {
        StartView view = new StartView();
        StartPresenter controller = new StartPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                StartPresenter.class.getResource("/startPage.css")).toExternalForm());

        stage.setTitle("Meditate Easy");
        stage.setScene(scene);
        stage.show();
    }
}