package med.easy.meditateeasy.view.Video;

import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.model.Video;

public class VideoDetailPresenter {
    private final VideoDetailView view;
    private final Stage stage;
    private final Video video;

    public VideoDetailPresenter(VideoDetailView view, Stage stage, Video video) {
        this.view = view;
        this.stage = stage;
        this.video = video;
        setupView();
        attachEvents();
    }

    private void setupView() {
        view.setVideo(video);
        Scene scene = new Scene(view.getRoot());
        String cssPath = getClass().getResource("/detailPage.css").toExternalForm();
        scene.getStylesheets().add(cssPath);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void attachEvents() {
        view.getBackButton().setOnAction(e -> {
            VideoPresenter.show(stage);
        });
    }

    public static void show(Stage stage, Video video) {
        VideoDetailView view = new VideoDetailView();
        new VideoDetailPresenter(view, stage, video);
    }
}
