package med.easy.meditateeasy.view.Video;

import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.VideoRepository;
import med.easy.meditateeasy.model.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import med.easy.meditateeasy.view.Instruction.InstructionPresenter;
import med.easy.meditateeasy.view.Login.LoginPresenter;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.List;
import java.util.Objects;

public class VideoPresenter {
    private final VideoView view;
    private final VideoRepository videoRepository;
    private final ObservableList<Video> videoList = FXCollections.observableArrayList();

    private VideoPresenter(VideoView view) {
        this.view = view;
        this.videoRepository = new VideoRepository();

        attachEvents();
        loadVideos();
        bindVideoList();
    }

    private void attachEvents() {
        // Navigation
        view.getNavBar().getVideoBtn().setOnAction(event -> {
            VideoPresenter.show(view.getStage());
        });

        view.getNavBar().getInstructionBtn().setOnAction(event -> {
            InstructionPresenter.show(view.getStage());
        });

        view.getNavBar().getHomeBtn().setOnAction(event -> {
            StartPresenter.show(view.getStage());
        });

        view.getNavBar().getLoginBtn().setOnAction(e -> {
            LoginPresenter.show(view.getStage());
        });

        view.getVideoListView().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                VideoDetailPresenter.show(view.getStage(), newVal);
            }
        });
    }

    private void loadVideos() {
        List<Video> allVideos = videoRepository.getAllVideos();
        videoList.setAll(allVideos);
    }

    private void bindVideoList() {
        view.getVideoListView().setItems(videoList);
    }

    public static void show(Stage stage) {
        VideoView view = new VideoView();
        new VideoPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                VideoPresenter.class.getResource("/videoPage.css")).toExternalForm());

        stage.setTitle("Meditate Easy - Anleitungsvideos");
        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }
}
