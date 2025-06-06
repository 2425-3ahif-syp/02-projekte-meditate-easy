package med.easy.meditateeasy.view.Login;

import javafx.stage.Stage;
import med.easy.meditateeasy.database.AdminRepository;
import javafx.scene.Scene;
import med.easy.meditateeasy.util.Toast;
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
            Toast.show(view.getStage(), "Erfolgreich eingeloggt!", Toast.ToastType.SUCCESS, 1000);
            AdminDashboardPresenter.show(view.getStage());
        } else {
            Toast.show(view.getStage(), "Ungültiger Benutzername oder Passwort!", Toast.ToastType.ERROR, 1000);
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
        stage.setTitle("Meditate Easy - Login");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
