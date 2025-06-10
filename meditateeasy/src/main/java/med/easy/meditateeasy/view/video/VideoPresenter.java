package med.easy.meditateeasy.view.video;

import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import med.easy.meditateeasy.database.DifficultyRepository;
import med.easy.meditateeasy.database.VideoRepository;
import med.easy.meditateeasy.model.Difficulty;
import med.easy.meditateeasy.model.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import med.easy.meditateeasy.view.instruction.InstructionPresenter;
import med.easy.meditateeasy.view.login.LoginPresenter;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.List;
import java.util.Objects;

public class VideoPresenter {
    private final VideoView view;
    private final VideoRepository videoRepository;
    private final ObservableList<Video> videoList = FXCollections.observableArrayList();
    private final DifficultyRepository difficultyRepository = new DifficultyRepository();
    private final FilteredList<Video> filteredVideos;

    private VideoPresenter(VideoView view) {
        this.view = view;
        this.videoRepository = new VideoRepository();
        this.filteredVideos = new FilteredList<>(videoList);
        loadDifficulties();
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
        view.getVideoListView().setItems(filteredVideos);

        view.getSearchField().textProperty().addListener((obs, old, newVal) -> updateFilter());
        view.getDifficultyFilter().getSelectionModel().selectedItemProperty()
                .addListener((obs, old, newVal) -> updateFilter());
    }

    private void updateFilter() {
        String searchText = view.getSearchField().getText().toLowerCase();
        String difficulty = view.getDifficultyFilter().getValue();

        filteredVideos.setPredicate(video ->
                video.getTitle().toLowerCase().contains(searchText) &&
                        (difficulty.equals("Alle") || video.getDifficulty().toString().equalsIgnoreCase(difficulty))
        );
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

    private void loadDifficulties() {
        view.getDifficultyFilter().getItems().clear();
        view.getDifficultyFilter().getItems().add("Alle");

        for (Difficulty diff : difficultyRepository.getAllDifficulties()) {
            view.getDifficultyFilter().getItems().add(diff.getDescription());
        }

        view.getDifficultyFilter().setValue("Alle");
    }

}
