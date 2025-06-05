package med.easy.meditateeasy.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboardView {
    private final VBox root = new VBox();

    private final Label titleLabel = new Label("Admin Dashboard");

    public AdminDashboardView() {
        init();
    }

    private void init() {
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        titleLabel.getStyleClass().add("dashboard-title");

        root.getChildren().addAll(titleLabel);
    }

    public VBox getRoot() {
        return root;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }


    public Label getTitleLabel() {
        return titleLabel;
    }
}
