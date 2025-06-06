package med.easy.meditateeasy.view.AdminDashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import med.easy.meditateeasy.MeditateEasyApp;

public class AdminDashboardView {
    private final VBox root = new VBox();
    private final Label titleLabel = new Label("Admin Dashboard");

    private final TabPane tabPane = new TabPane();
    private final Button backButton = new Button("← Zurück");

    public final Tab difficultiesTab = new Tab("Difficulties");
    public final Tab instructionsTab = new Tab("Instructions");
    public final Tab videosTab = new Tab("Videos");

    public AdminDashboardView() {
        init();
    }

    private void init() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);

        titleLabel.getStyleClass().add("dashboard-title");
        backButton.getStyleClass().add("back-button");
        backButton.setMinWidth(80);

        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10, 20, 10, 20));

        VBox leftBox = new VBox(backButton);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftBox);
        topBar.setCenter(titleLabel);
        topBar.setPrefWidth(Double.MAX_VALUE);

        tabPane.getTabs().addAll(difficultiesTab, instructionsTab, videosTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.getChildren().addAll(topBar, tabPane);
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
