package med.easy.meditateeasy.view.Video;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.model.Video;

public class VideoDetailView {
    private final VBox root = new VBox();
    private final HBox header = new HBox();
    private final Button backButton = new Button("← Zurück");
    private final Label titleLabel = new Label();
    private final Label difficultyLabel = new Label();
    private final WebView webView = new WebView();

    public VideoDetailView() {
        header.getChildren().add(backButton);
        header.getStyleClass().add("detail-header");
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());
        root.setSpacing(20);
        root.getStyleClass().add("detail-container");
        titleLabel.getStyleClass().add("detail-title");
        difficultyLabel.getStyleClass().add("detail-difficulty");

        root.getChildren().addAll(header, titleLabel, difficultyLabel, webView);
    }

    public void setVideo(Video video) {
        titleLabel.setText(video.getTitle());
        difficultyLabel.setText("Schwierigkeit: " + video.getDifficulty());
        webView.getEngine().load(video.getLink());
    }

    public VBox getRoot() {
        return root;
    }
    public Button getBackButton() {
        return backButton;
    }

    public WebView getWebView() {
        return webView;
    }
}
