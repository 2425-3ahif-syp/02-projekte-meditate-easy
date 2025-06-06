package med.easy.meditateeasy.view.Video;

import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import med.easy.meditateeasy.MeditateEasyApp;
import med.easy.meditateeasy.model.Video;
import med.easy.meditateeasy.util.NavBarView;

public class VideoView {
    private final VBox root = new VBox();
    private final NavBarView navBar = new NavBarView();



    // Video list
    private final ListView<Video> videoListView = new ListView<>();

    public VideoView() {
        init();
    }

    private void init() {
        root.setPrefWidth(MeditateEasyApp.getX());
        root.setPrefHeight(MeditateEasyApp.getY());


        videoListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Video> call(ListView<Video> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Video item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(String.format("%s\nSchwierigkeit: %s",
                                    item.getTitle(), item.getDifficulty()));
                        }
                    }
                };
            }
        });

        VBox.setVgrow(videoListView, Priority.ALWAYS);

        root.getChildren().addAll(navBar, videoListView);
    }

    public VBox getRoot() {
        return root;
    }

    public ListView<Video> getVideoListView() {
        return videoListView;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }

    public NavBarView getNavBar() {
        return navBar;
    }
}
