package med.easy.meditateeasy.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.MeditateEasyApp;

import java.util.Objects;

public class StartView {
    private final VBox root = new VBox();
    private final NavBarView navBar = new NavBarView();



    // Welcome text and image
    private final HBox welcomeContainer = new HBox();
    private final Label welcomeText = new Label("Welcome to Meditate Easy!");
    private final ImageView welcomeImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/welcome-image.png"))));
    public StartView() {
        init();
    }

    private void init() {
        // Root
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());





        // Welcome container
        welcomeContainer.getChildren().addAll(welcomeText, welcomeImage);
        welcomeContainer.setAlignment(Pos.CENTER);
        welcomeContainer.setSpacing(40);
        welcomeContainer.getStyleClass().add("welcome-container");

        // Welcome text
        welcomeText.getStyleClass().add("welcome-text");

        // Welcome image
        welcomeImage.setFitWidth(400);
        welcomeImage.setPreserveRatio(true);
        welcomeImage.getStyleClass().add("welcome-image");

        // Generate root view
        root.getChildren().addAll(navBar, welcomeContainer);
    }

    public VBox getRoot() {
        return root;
    }


    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }



    public NavBarView getNavBar() {
        return navBar;
    }
}
