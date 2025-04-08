package med.easy.meditateeasy;

import javafx.application.Application;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DBManager;
import med.easy.meditateeasy.database.Database;
import med.easy.meditateeasy.view.StartPresenter;

import java.io.IOException;

public class MeditateEasyApp extends Application {


    @Override
    public void start(Stage startStage) throws IOException {
        DBManager.startDatabase();
        Database.getInstance();
        Database.getInstance().insertTestData();

        startStage.setMaximized(true);
        StartPresenter.show(startStage);

    }

    @Override
    public void stop() throws Exception {
        Database.getInstance().closeConnection();
        DBManager.stopDatabase();
    }

    public static void main(String[] args) {
        launch();
    }


}