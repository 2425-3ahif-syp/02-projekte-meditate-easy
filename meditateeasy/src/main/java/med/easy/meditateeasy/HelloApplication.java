package med.easy.meditateeasy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DBManager;
import med.easy.meditateeasy.database.Database;
import org.h2.store.Data;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBManager.startDatabase();
        Database.getInstance();

    }

    public static void main(String[] args) {
        launch();
    }
}