package med.easy.meditateeasy.view.Login;

import javafx.stage.Stage;
import med.easy.meditateeasy.database.AdminRepository;
import javafx.scene.Scene;
import med.easy.meditateeasy.view.AdminDashboard.AdminDashboardPresenter;
import med.easy.meditateeasy.view.StartPresenter;

import java.util.Objects;

public class LoginPresenter {

    private final LoginView view;
    private final AdminRepository adminRepository;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.adminRepository = new AdminRepository();
        attachEvents();
    }

    private void attachEvents() {
        view.getLoginButton().setOnAction(e -> {
            handleLogin();
        });
        view.getBackButton().setOnAction(e -> {
            StartPresenter.show(view.getStage());
        });
    }

    private void handleLogin() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (adminRepository.verifyUser(username, password)) {
            AdminDashboardPresenter.show(view.getStage());
        } else {
            view.setMessage("Ungültiger Benutzername oder Passwort!", "red");
        }
    }

    public LoginView getView() {
        return view;
    }

    public static void show(Stage stage) {
        LoginView view = new LoginView();
        new LoginPresenter(view);

        Scene scene = new Scene(view.getRoot());
        scene.getStylesheets().add(Objects.requireNonNull(
                LoginPresenter.class.getResource("/login.css")).toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
