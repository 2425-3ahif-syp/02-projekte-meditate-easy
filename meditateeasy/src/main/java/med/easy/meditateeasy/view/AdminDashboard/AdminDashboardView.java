package med.easy.meditateeasy.view.AdminDashboard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class AdminDashboardView {
    private final VBox root = new VBox();
    private final Label titleLabel = new Label("Admin Dashboard");

    private final TabPane tabPane = new TabPane();

    public final Tab difficultiesTab = new Tab("Difficulties");
    public final Tab instructionsTab = new Tab("Instructions");
    public final Tab videosTab = new Tab("Videos");

    public AdminDashboardView() {
        init();
    }

    private void init() {
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);

        titleLabel.getStyleClass().add("dashboard-title");

        tabPane.getTabs().addAll(difficultiesTab, instructionsTab, videosTab);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        root.getChildren().addAll(titleLabel, tabPane);
    }

    public VBox getRoot() {
        return root;
    }
}
