package med.easy.meditateeasy.view.Login;

import javafx.stage.Stage;
import med.easy.meditateeasy.database.AdminRepository;
import javafx.scene.Scene;
import med.easy.meditateeasy.view.AdminDashboard.AdminDashboardPresenter;

public class LoginPresenter {

    private final LoginView view;
    private final AdminRepository adminRepository;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.adminRepository = new AdminRepository();

        view.getLoginButton().setOnAction(e -> {
            handleLogin();
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
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
