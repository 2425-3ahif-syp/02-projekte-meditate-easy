package med.easy.meditateeasy.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import med.easy.meditateeasy.model.Video;

public class VideoView {
    private final VBox root = new VBox();

    // Navigation bar
    private final HBox navBar = new HBox();
    private final Button videoBtn = new Button("Anleitungsvideos");
    private final Button instructionBtn = new Button("Instruktionen");
    private final Button homeBtn = new Button("Home");

    // Video list
    private final ListView<Video> videoListView = new ListView<>();

    public VideoView() {
        init();
    }

    private void init() {
        root.setPrefWidth(1600);
        root.setPrefHeight(1000);

        // Navigation bar setup
        navBar.getChildren().addAll(homeBtn, videoBtn, instructionBtn);
        navBar.getStyleClass().add("navbar");
        homeBtn.getStyleClass().add("home-btn");
        videoBtn.getStyleClass().add("video-btn");
        instructionBtn.getStyleClass().add("instruction-btn");

        // ListView cell formatting
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

        // Add all components to root
        root.getChildren().addAll(navBar, videoListView);
    }

    public VBox getRoot() {
        return root;
    }

    public Button getVideoBtn() {
        return videoBtn;
    }

    public Button getInstructionBtn() {
        return instructionBtn;
    }

    public Button getHomeBtn() {
        return homeBtn;
    }

    public ListView<Video> getVideoListView() {
        return videoListView;
    }

    public Stage getStage() {
        return (Stage) root.getScene().getWindow();
    }
}
