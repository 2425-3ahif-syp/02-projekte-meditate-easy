package med.easy.meditateeasy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.Database;
import med.easy.meditateeasy.view.StartPresenter;

import java.io.IOException;

public class MeditateEasyApp extends Application {

    private static double x;
    private static double y;

    @Override
    public void start(Stage startStage) throws IOException {
        Database.getInstance();

        startStage.setMaximized(true);
        StartPresenter.show(startStage);
        Platform.runLater(() -> { // um es auszuführen, wenn es dann wirklich angezeigt wird (callback)
            setX(startStage.getWidth());
            setY(startStage.getHeight());
        });
    }

    @Override
    public void stop() throws Exception {
        Database.getInstance().closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }

    public static double getX() {
        return x;
    }

    public void setX(double x) {
        MeditateEasyApp.x = x;
    }

    public static double getY() {
        return y;
    }

    public void setY(double y) {
        MeditateEasyApp.y = y;
    }
}