package med.easy.meditateeasy.util;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class NavBarView extends HBox {
    private final Button videoBtn = new Button("Anleitungsvideos");
    private final Button instructionBtn = new Button("Instruktionen");
    private final Button homeBtn = new Button("Home");
    private final Button loginBtn = new Button("Login");
    private final Region spacer = new Region();

    public NavBarView() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getStyleClass().add("navbar");

        HBox.setHgrow(spacer, Priority.ALWAYS);
        getChildren().addAll(homeBtn, videoBtn, instructionBtn, spacer, loginBtn);

        videoBtn.getStyleClass().add("video-btn");
        instructionBtn.getStyleClass().add("instruction-btn");
        homeBtn.getStyleClass().add("home-btn");
        loginBtn.getStyleClass().add("login-btn");

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

    public Button getLoginBtn() {
        return loginBtn;
    }
}
