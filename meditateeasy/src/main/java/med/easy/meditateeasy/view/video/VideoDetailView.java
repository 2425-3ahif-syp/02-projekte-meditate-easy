package med.easy.meditateeasy.view.video;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.model.Video;
import med.easy.meditateeasy.util.Stopwatch;

public class VideoDetailView {
    private final VBox root = new VBox();
    private final HBox header = new HBox();
    private final Button backButton = new Button("← Zurück");
    private final Label titleLabel = new Label();
    private final Label difficultyLabel = new Label();
    private final WebView webView = new WebView();
    private final Stopwatch stopwatch = new Stopwatch();
    private final HBox contentContainer = new HBox(20);
    private final VBox videoContainer = new VBox(20);

    public VideoDetailView() {
        header.getChildren().add(backButton);
        header.getStyleClass().add("detail-header");
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());
        root.setSpacing(20);
        root.getStyleClass().add("detail-container");
        titleLabel.getStyleClass().add("detail-title");
        difficultyLabel.getStyleClass().add("detail-difficulty");

        contentContainer.setAlignment(Pos.CENTER);
        stopwatch.setAlignment(Pos.TOP_CENTER);
        contentContainer.getChildren().addAll(videoContainer, stopwatch);
        videoContainer.getChildren().addAll(titleLabel, difficultyLabel, webView);

        root.getChildren().addAll(header, contentContainer);

        root.getStylesheets().add(getClass().getResource("/stopwatch.css").toExternalForm());
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
